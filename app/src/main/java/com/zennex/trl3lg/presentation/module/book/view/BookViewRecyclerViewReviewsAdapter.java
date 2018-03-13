package com.zennex.trl3lg.presentation.module.book.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.utils.view.list.viewholder.ReviewViewHolder;

/**
 * Created by nikita on 20.10.17.
 */

public class BookViewRecyclerViewReviewsAdapter extends BaseRecyclerViewAdapter<ReviewViewHolder, Review> {

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(getItemList().get(position));
    }

}
