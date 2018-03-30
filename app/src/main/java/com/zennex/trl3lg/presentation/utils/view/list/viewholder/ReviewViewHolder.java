package com.zennex.trl3lg.presentation.utils.view.list.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Review;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.review_item_layout_usefulness)
    ViewGroup vgUsefulness;

    @BindView(R.id.review_item_iv_yes)
    ImageView ivYes;

    @BindView(R.id.review_item_iv_no)
    ImageView ivNo;

    private ReviewUsefulnessListener listener;
    private Review review;

    public ReviewViewHolder(View itemView, ReviewUsefulnessListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.listener = listener;
    }

    public void bind(Review review) {
        this.review = review;
        Context context = mCreatedDate.getContext();

        mTvName.setText(review.getMemberName());
        mRatingBar.setRating(review.getRating());
        mCreatedDate.setText(context.getString(R.string.review_date, review.getCreateDate()));
        mTvText.setText(review.getText());

        if (review.isRated())
            vgUsefulness.setVisibility(View.GONE);
        else
            vgUsefulness.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.review_item_iv_yes)
    void onYesButtonClicked() {
        vgUsefulness.setVisibility(View.GONE);
        listener.rateReview(review.getId(), true    );
    }

    @OnClick(R.id.review_item_iv_no)
    void onNoButtonClicked() {
        vgUsefulness.setVisibility(View.GONE);
        listener.rateReview(review.getId(), false);
    }

    public interface ReviewUsefulnessListener {
        void rateReview(String reviewId, boolean isUseful);
    }
}