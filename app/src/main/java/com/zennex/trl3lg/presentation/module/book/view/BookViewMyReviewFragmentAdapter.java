package com.zennex.trl3lg.presentation.module.book.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.view.MyCommentViewFragment;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.view.MyRatingViewFragment;

/**
 * Created by nikita on 28.09.17.
 */

public class BookViewMyReviewFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments = {MyRatingViewFragment.newInstance(),
            MyCommentViewFragment.newInstance()};


    public BookViewMyReviewFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.length;
    }


}
