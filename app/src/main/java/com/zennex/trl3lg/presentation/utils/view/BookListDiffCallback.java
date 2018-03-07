package com.zennex.trl3lg.presentation.utils.view;

import android.support.v7.util.DiffUtil;

import com.zennex.trl3lg.domain.entities.Book;

import java.util.List;

/**
 * Created by nikita on 10.08.17.
 */

public class BookListDiffCallback extends DiffUtil.Callback {

    private final List<Book> mOldBookList;
    private final List<Book> mNewBookList;
    private final int mCountFooters;

    public BookListDiffCallback(List<Book> oldBookList, List<Book> newBookList, int countFooters) {
        mOldBookList = oldBookList;
        mNewBookList = newBookList;
        mCountFooters = countFooters;
    }

    @Override
    public int getOldListSize() {
        return mOldBookList.size() + mCountFooters;
    }

    @Override
    public int getNewListSize() {
        return mNewBookList.size() + mCountFooters;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (isFooter(oldItemPosition, newItemPosition)) return false;
        return mOldBookList.get(oldItemPosition).getId().equals(mNewBookList.get(newItemPosition).getId());
    }

    private boolean isFooter(int oldItemPosition, int newItemPosition) {
        return (oldItemPosition >= mOldBookList.size()) |
                (newItemPosition >= mNewBookList.size());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldBookList.get(oldItemPosition).equals(mNewBookList.get(newItemPosition));
    }
}
