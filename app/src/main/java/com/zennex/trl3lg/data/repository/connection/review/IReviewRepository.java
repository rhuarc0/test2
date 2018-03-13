package com.zennex.trl3lg.data.repository.connection.review;

import com.zennex.trl3lg.domain.entities.Review;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 20.10.17.
 */

public interface IReviewRepository {

    Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count);

}
