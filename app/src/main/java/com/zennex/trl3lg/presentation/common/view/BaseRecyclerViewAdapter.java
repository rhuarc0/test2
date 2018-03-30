package com.zennex.trl3lg.presentation.common.view;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, Entity>
        extends RecyclerView.Adapter<VH> {

    private List<Entity> mItemList = new ArrayList<>();

    public BaseRecyclerViewAdapter() {
        mItemList = new ArrayList<>();
    }

    public BaseRecyclerViewAdapter(List<Entity> items) {
        setItemList(items);
    }

    public List<Entity> getItemList() {
        return mItemList;
    }

    public void setItemList(List<Entity> itemList) {
        mItemList.clear();
        mItemList.addAll(itemList);
    }

    public void updateList(List<Entity> list) {
        setItemList(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        if (mItemList == null) mItemList = new ArrayList<>();
        else mItemList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
