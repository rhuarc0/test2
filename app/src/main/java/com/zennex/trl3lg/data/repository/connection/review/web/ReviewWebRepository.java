package com.zennex.trl3lg.data.repository.connection.review.web;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.data.entity.dto.ReviewDto;
import com.zennex.trl3lg.data.entity.rest.request.FetchReviewsRequest;
import com.zennex.trl3lg.data.entity.rest.response.BaseResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchReviewsResponse;
import com.zennex.trl3lg.data.mapper.ReviewDtoMapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.review.web.service.IReviewWebService;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewWebRepository implements IReviewWebRepository {


    @Inject
    IReviewWebService mReviewWebService;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    ReviewDtoMapper mReviewDtoMapper;

    @Inject
    public ReviewWebRepository() {
    }

    @Override
    public Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count) {
        return Observable.fromCallable(() -> this.createRequests(bookId, startPosition, count))
                .flatMap(mReviewWebService::fetchItemReviews)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(getDataReviews())
                .map(combineReviewDtos())
                .map(transformReviewDtos());
    }


    private List<FetchReviewsRequest> createRequests(String bookId, int startPosition, int count) {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(renatalModuleid -> FetchReviewsRequest.newInstance(renatalModuleid,
                        new FetchReviewsRequest.Data(mAuthRepository.getSessionToken().blockingSingle(),
                                bookId,
                                String.valueOf(startPosition),
                                String.valueOf(count))))
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


}
