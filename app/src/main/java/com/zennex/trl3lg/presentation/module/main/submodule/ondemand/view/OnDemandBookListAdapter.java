package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.drugandswipe.ItemTouchHelperViewHolder;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.drugandswipe.OnStartDrugListener;
import com.zennex.trl3lg.presentation.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by nikit on 26.08.2017.
 */

public class OnDemandBookListAdapter extends BaseRecyclerViewAdapter<OnDemandBookListAdapter.ViewHolderItem, AudioBook> {

    private final OnStartDrugListener mDrugStartListener;

    private IOnDemandBookListener mOnDemandBookListener;

    public interface IOnDemandBookListener {
        void onBookSelected(int position, List<Pair<View, String>> animViews);
    }

    public OnDemandBookListAdapter(OnStartDrugListener dragStartListener) {
        mDrugStartListener = dragStartListener;
    }

    public IOnDemandBookListener getOnClickItemListener() {
        return mOnDemandBookListener;
    }

    public void setOnClickItemListener(IOnDemandBookListener onClickItemListener) {
        mOnDemandBookListener = onClickItemListener;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frg_on_demand_li, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {
        holder.bind(position);
    }

    class ViewHolderItem extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        @BindView(R.id.frg_on_demand_li_root_layout)
        CardView mRootCv;
        @BindView(R.id.frg_on_demand_li_iv_book)
        ImageView mIvBook;
        @BindView(R.id.frg_on_demand_li_tv_title)
        TextView mTvTitle;
        @BindView(R.id.frg_on_demand_li_tv_status)
        TextView mTvStatus;
        @BindView(R.id.frg_on_demand_handle_btn_drag)
        ImageButton mBtnDrag;
        @BindView(R.id.frg_on_demand_btn_activate)
        Button mBtnActivate;
        @BindView(R.id.frg_on_demand_li_tv_days_left)
        TextView mTvDaysLeft;
        Bitmap mBitmapIconRemove;
        RectF mRectFIconRemove;

        ViewHolderItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBitmapIconRemove = BitmapFactory.decodeResource(itemView.getContext().getResources(), R.drawable.ic_trash_red);
        }

        void bind(int position) {
            AudioBook book = getItemList().get(position);

            GlideApp.with(itemView.getContext())
                    .load(book.getImage())
                    .placeholder(R.drawable.image_placeholder)
                    .into(mIvBook);

            mTvTitle.setText(book.getTitle());

        }

        @OnClick(R.id.frg_on_demand_li_root_layout)
        void onItemClick() {
            if (mOnDemandBookListener != null) {
                mOnDemandBookListener.onBookSelected(getAdapterPosition(), Stream.of(mIvBook, mTvTitle).
                        map(view -> Pair.create(view, view.getTransitionName()))
                        .toList());
            }
        }

        @OnTouch(R.id.frg_on_demand_handle_btn_drag)
        public boolean onTouchBtnDrag(MotionEvent event) {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mDrugStartListener.onStartDrag(this);
                return true;
            }
            return false;
        }

        //region ItemTouchHelperViewHolder

        @Override
        public void onItemSwipe(Canvas c, Paint paint, float dX, float dY) {
            drawIconRemove(c, paint);
            itemView.setTranslationX(dX);
        }

        private void drawIconRemove(Canvas canvas, Paint paint) {
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;
            mRectFIconRemove = new RectF((float) itemView.getRight() - 2 * width,
                    (float) itemView.getTop() + width,
                    (float) itemView.getRight() - width,
                    (float) itemView.getBottom() - width);
            canvas.drawBitmap(mBitmapIconRemove, null, mRectFIconRemove, paint);
        }

        @Override
        public void onItemSelected() {
            mRootCv.setCardElevation(itemView.getContext().getResources().getDimension(R.dimen.on_demand_list_item_card_elevation_selected));
        }

        @Override
        public void onItemClear() {
            mRootCv.setCardElevation(itemView.getContext().getResources().getDimension(R.dimen.on_demand_list_item_card_elevation_normal));
            mRootCv.setTranslationX(0);
        }

        //endregion ItemTouchHelperViewHolder

    }
}
