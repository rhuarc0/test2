package com.zennex.trl3lg.presentation.utils.view;

import android.support.v7.util.DiffUtil;

import com.zennex.trl3lg.domain.entities.Review;

import java.util.List;

/**
 * Created by nikita on 22.10.17.
 */

public class ReviewListDiffCallback extends DiffUtil.Callback {

    private List<Review> mOldReviewList;
    private List<Review> mNewReviewList;

    public ReviewListDiffCallback(List<Review> oldReviewList, List<Review> newReviewList) {
        mOldReviewList = oldReviewList;
        mNewReviewList = newReviewList;
    }

    @Override
    public int getOldListSize() {
        return mOldReviewList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewReviewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldReviewList.get(oldItemPosition).getId().equals(mNewReviewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldReviewList.get(oldItemPosition).equals(mNewReviewList.get(newItemPosition));
    }
}
