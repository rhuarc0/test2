package com.zennex.trl3lg.data.datasource.review;

import android.util.Pair;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.request.book.SetReviewUsefulRequest;
import com.zennex.trl3lg.data.rest.response.book.SetReviewUsefulResponse;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.data.entity.dto.ReviewDto;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.rest.IReviewWebService;
import com.zennex.trl3lg.data.rest.request.book.FetchReviewsRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchReviewsResponse;
import com.zennex.trl3lg.data.mapper.dtomapper.ReviewDtoMapper;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewDataSource implements IReviewDataSource {

    @Inject
    IReviewWebService mReviewWebService;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    ReviewDtoMapper mReviewDtoMapper;

    @Inject
    public ReviewDataSource() {
    }

    @Override
    public Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count) {
        return Observable.fromCallable(() -> this.createFetchReviewRequests(bookId, startPosition, count))
                .flatMap(mReviewWebService::fetchItemReviews)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(getDataReviews())
                .map(combineReviewDtos())
                .map(transformReviewDtos());
    }

    @Override
    public Observable<Pair<String, Boolean>> setReviewUseful(String reviewId, boolean useful) {
        return Observable.fromCallable(() -> this.createSetReviewUsefulRequest(reviewId, useful))
                .flatMap(mReviewWebService::setReviewUseful)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .flatMap(transformSetViewUseful(reviewId, useful));
    }

    //region FetchReviews

    private List<FetchReviewsRequest> createFetchReviewRequests(String bookId, int startPosition, int count) {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> FetchReviewsRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle(),
                        bookId,
                        String.valueOf(startPosition),
                        String.valueOf(count)))
                .toList();
    }

    private Function<List<FetchReviewsResponse>, List<List<ReviewDto>>> getDataReviews() {
        return responses -> Stream.of(responses)
                .map(BaseResponse::getData)
                .toList();
    }

    private Function<List<List<ReviewDto>>, List<ReviewDto>> combineReviewDtos() {
        return lists -> Stream.of(lists)
                .flatMap(Stream::of)
                .toList();
    }

    private Function<List<ReviewDto>, List<Review>> transformReviewDtos() {
        return reviewDtos -> mReviewDtoMapper.execute(reviewDtos);
    }

    //endregion

    //region SetReviewUseful

    private final static String IS_USEFUL = "1";
    private final static String IS_NOT_USEFUL = "0";

    private List<SetReviewUsefulRequest> createSetReviewUsefulRequest(String reviewId, boolean isUseful) {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> SetReviewUsefulRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle(),
                        reviewId,
                        isUseful ? IS_USEFUL : IS_NOT_USEFUL))
                .toList();
    }

    private Function<? super SetReviewUsefulResponse, ? extends Observable<Pair<String, Boolean>>> transformSetViewUseful(String reviewId, boolean useful) {
        return setReviewUsefulResponse -> {
            if (setReviewUsefulResponse == null)
                return Observable.error(new Exception("Something went wrong")); // ToDo

            return Observable.just(Pair.create(reviewId, useful));
        };
    }


    //endregion
}
