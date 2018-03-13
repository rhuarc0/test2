package com.zennex.trl3lg.presentation.module.main.submodule.catalog.view;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.zennex.trl3lg.domain.entities.RentalGroup;

import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */

public class CatalogDiffCallback extends DiffUtil.Callback {

    @NonNull
    private final List<RentalGroup> mNewList;
    @NonNull
    private final List<RentalGroup> mOldList;

    public CatalogDiffCallback(@NonNull List<RentalGroup> newList, @NonNull List<RentalGroup> oldList) {
        mNewList = newList;
        mOldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }
}
