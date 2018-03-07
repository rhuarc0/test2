package com.zennex.trl3lg.presentation.module.review.view;

import android.annotation.SuppressLint;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.utils.view.ReviewListDiffCallback;
import com.zennex.trl3lg.presentation.utils.view.list.viewholder.ReviewViewHolder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReviewsModuleRecyclerAdapter extends BaseRecyclerViewAdapter<ReviewViewHolder, Review> {

    @Override
    public void updateList(List<Review> list) {
        Observable.fromCallable(() -> DiffUtil.calculateDiff(new ReviewListDiffCallback(getItemList(), list), false))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    setItemList(list);
                    diffResult.dispatchUpdatesTo(this);
                });
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_review_item, parent, false));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(getItemList().get(position));
    }

}
