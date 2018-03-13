package com.zennex.trl3lg.presentation.module.main.submodule.catalog.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.RentalGroup;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.common.view.IOnClickItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CatalogRecyclerViewAdapter extends BaseRecyclerViewAdapter<CatalogRecyclerViewAdapter.ViewHolder, RentalGroup> {

    private IOnClickItemListener mClickItemListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frg_catalog_li, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RentalGroup rentalGroup = getItemList().get(position);
        holder.mTvNameRental.setText(rentalGroup.getTitle());
    }


    public IOnClickItemListener getClickItemListener() {
        return mClickItemListener;
    }

    void setClickItemListener(IOnClickItemListener clickItemListener) {
        mClickItemListener = clickItemListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.frg_catalog_li_tv_rental_name)
        TextView mTvNameRental;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.frg_catalog_content)
        void onClickItem() {
            if (mClickItemListener != null) mClickItemListener.onClickItem(getAdapterPosition());
        }


    }

}
