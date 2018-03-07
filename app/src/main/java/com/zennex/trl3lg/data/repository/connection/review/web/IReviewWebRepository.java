package com.zennex.trl3lg.data.repository.connection.review.web;

import com.zennex.trl3lg.data.entity.Review;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 20.10.17.
 */

public interface IReviewWebRepository {

    Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count);

}
