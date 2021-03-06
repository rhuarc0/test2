package com.zennex.trl3lg.data.datasource.review;

import android.util.Pair;

import com.zennex.trl3lg.domain.entities.Review;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 20.10.17.
 */

public interface IReviewDataSource {

    Observable<List<Review>> fetchReviews(String bookId, int startPosition, int count);
    Observable<Pair<String, Boolean>> setReviewUseful(String reviewId, boolean useful);

}
