package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.data.entity.dto.ReviewDto;

import javax.inject.Inject;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewDtoMapper extends Mapper<ReviewDto, Review> {

    private final static String IS_RATED = "1";

    @Inject
    public ReviewDtoMapper() {
        super(Review::new);
    }

    @NonNull
    @Override
    protected Review transform(@NonNull ReviewDto reviewDto, Review review) {
        review.setId(reviewDto.getId());
        review.setRating(Float.parseFloat(reviewDto.getRating()));
        review.setMemberId(reviewDto.getMemberId());
        review.setUseFulYes(reviewDto.getUseFulYes());
        review.setUseFulTotal(reviewDto.getUseFulTotal());
        review.setCreateDate(reviewDto.getCreateDate());
        review.setMemberName(reviewDto.getMemberName());
        review.setMemberId(reviewDto.getMemberId());
        review.setMemberExtra(reviewDto.getMemberExtra());
        review.setText(reviewDto.getText());
        review.setRated(reviewDto.getRated().equals(IS_RATED));
        return review;
    }
}
