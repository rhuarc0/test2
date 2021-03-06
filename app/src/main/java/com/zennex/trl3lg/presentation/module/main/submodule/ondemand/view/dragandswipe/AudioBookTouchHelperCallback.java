package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.dragandswipe;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.OnDemandBookListAdapter;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final String TAG = AudioBookTouchHelperCallback.class.getSimpleName();

    private final ItemTouchHelperListener mTouchHelperListener;

    private Paint mPaint = new Paint();

    private Integer mDragFrom = null;
    private Integer mDragTo = null;

    public AudioBookTouchHelperCallback(ItemTouchHelperListener touchHelperListener) {
        mTouchHelperListener = touchHelperListener;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mTouchHelperListener.isItemViewSwipeEnabled();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mTouchHelperListener.onItemDismiss(viewHolder);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == OnDemandBookListAdapter.TYPE_DRAGGABLE) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType())
            return false;
        return mTouchHelperListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            ((ItemTouchHelperViewHolder) viewHolder).onItemSwipe(c, mPaint,dX, dY);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        if (mDragFrom == null) mDragFrom = fromPos;
        mDragTo = toPos;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    itemViewHolder.onItemSelected();
                }
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }

        if (mDragTo != null && mDragFrom != null) {
            mTouchHelperListener.onDragFinish(mDragFrom, mDragTo);
        }

        mDragTo = mDragFrom = null;
    }
}
