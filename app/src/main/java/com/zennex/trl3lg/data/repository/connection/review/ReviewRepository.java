package com.zennex.trl3lg.data.repository.connection.review;

import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.data.repository.connection.review.web.IReviewWebRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewRepository implements IReviewRepository {

    @Inject
    IReviewWebRepository mReviewWebRepository;

    @Inject
    public ReviewRepository() {
    }

    @Override
    public Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count) {
        return mReviewWebRepository.fetchReviews(bookId, startPosition, count);
    }
}
