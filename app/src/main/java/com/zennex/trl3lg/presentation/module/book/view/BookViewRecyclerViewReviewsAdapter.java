package com.zennex.trl3lg.presentation.module.book.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.utils.view.list.viewholder.ReviewViewHolder;

/**
 * Created by nikita on 20.10.17.
 */

public class BookViewRecyclerViewReviewsAdapter extends BaseRecyclerViewAdapter<ReviewViewHolder, Review> {

    private ReviewViewHolder.ReviewUsefulnessListener listener;

    public BookViewRecyclerViewReviewsAdapter(ReviewViewHolder.ReviewUsefulnessListener listener) {
        this.listener = listener;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_review_item, parent, false);
        return new ReviewViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(getItemList().get(position));
    }

}
