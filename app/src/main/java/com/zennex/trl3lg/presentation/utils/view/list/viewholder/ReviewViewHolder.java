package com.zennex.trl3lg.presentation.utils.view.list.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikita on 22.10.17.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.act_book_review_item_tv_name)
    TextView mTvName;
    @BindView(R.id.act_book_review_item_rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.act_book_review_item_tv_created_date)
    TextView mCreatedDate;
    @BindView(R.id.act_book_review_item_tv_text)
    TextView mTvText;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Review review) {
        mTvName.setText(review.getMemberName());
        mRatingBar.setRating(review.getRating());
        mCreatedDate.setText("Date: " + review.getCreateDate());
        mTvText.setText(review.getText());
    }
}