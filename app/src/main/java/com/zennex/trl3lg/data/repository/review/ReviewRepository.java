package com.zennex.trl3lg.data.repository.review;

import com.zennex.trl3lg.data.datasource.review.IReviewDataSource;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.domain.repository.IReviewRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewRepository implements IReviewRepository {

    @Inject
    IReviewDataSource mReviewWebRepository;

    @Inject
    public ReviewRepository() {
    }

    @Override
    public Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count) {
        return mReviewWebRepository.fetchReviews(bookId, startPosition, count);
    }
}
