package com.zennex.trl3lg.presentation.module.search.view;

import android.annotation.SuppressLint;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.BuildConfig;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.presentation.common.view.BaseRecyclerViewAdapter;
import com.zennex.trl3lg.presentation.utils.GlideApp;
import com.zennex.trl3lg.presentation.utils.view.BookListDiffCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class SearchListRecyclerAdapter extends BaseRecyclerViewAdapter<RecyclerView.ViewHolder, Book> {

    public static final String TAG = "SearchListRecyclerAdapter";

    private boolean isStatusLoaded = false;

    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private final int mCountFooters = 1;
    private boolean mVisiblePendingFooter = true;
    private ISearchBookListAdapterListener mSearchBookListAdapterListener;

    public interface ISearchBookListAdapterListener {
        void onBookSelected(int position, List<Pair<View, String>> animateViews);
    }

    public ISearchBookListAdapterListener getSearchBookListAdapterListener() {
        return mSearchBookListAdapterListener;
    }

    public void setSearchBookListAdapterListener(ISearchBookListAdapterListener searchBookListAdapterListener) {
        mSearchBookListAdapterListener = searchBookListAdapterListener;
    }

    public void setStatusLoaded(boolean isStatusLoaded) {
        this.isStatusLoaded = isStatusLoaded;
    }

    @Override
    public void updateList(List<Book> list) {
        Observable.fromCallable(() -> DiffUtil.calculateDiff(new BookListDiffCallback(getItemList(), list, mCountFooters), false))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    setItemList(list);
                    diffResult.dispatchUpdatesTo(this);
                });
    }

    @Override
    public int getItemViewType(int position) {
        if (!isFooter(position)) return TYPE_ITEM;
        else return TYPE_FOOTER;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new SearchListRecyclerAdapter.ViewHolderItem(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.li_search_card, parent, false));
        } else {
            return new ViewHolderPendingFooter(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.li_footer_layout, parent, false));
        }
    }

    public boolean isVisiblePendingFooter() {
        return mVisiblePendingFooter;
    }


    public void showPendingFooter() {
        mVisiblePendingFooter = true;
    }

    public void hidePendingFooter() {
        mVisiblePendingFooter = false;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onBindViewHolder: position = " + String.valueOf(position));
        }
        if (!isFooter(position)) ((ViewHolderItem) holder).bind(position);
        else ((ViewHolderPendingFooter) holder).bind(isVisiblePendingFooter());
    }

    @Override
    public int getItemCount() {
        return getItemList().size() + mCountFooters;
    }

    private boolean isFooter(int position) {
        return (position > (getItemList().size() - 1));
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.act_search_pb)
        ProgressBar progressBar;

        @BindView(R.id.act_search_li_book_iv)
        ImageView mImageViewBook;

        @BindView(R.id.act_search_tv_title)
        TextView mTvTitle;

        @BindView(R.id.act_search_tv_author)
        TextView mTvAuthor;

        @BindView(R.id.act_search_tv_status)
        TextView tvStatus;


        @BindView(R.id.act_search_li_iv_type_book)
        ImageView mImageViewTypeBook;

        ViewHolderItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.act_search_li_root_layout)
        void onClickItem() {
            if (mSearchBookListAdapterListener != null && isStatusLoaded) {
                mSearchBookListAdapterListener.onBookSelected(getAdapterPosition(), Stream.of(mImageViewBook, mTvTitle)
                        .map(view -> Pair.create(view, view.getTransitionName())).toList());
            }
        }

        private void bind(int position) {
            Book book = getItemList().get(position);

            GlideApp.with(itemView.getContext())
                    .load(book.getImage())
                    .placeholder(R.drawable.image_placeholder)
                    .into(mImageViewBook);

            mTvTitle.setText(book.getTitle());

            mTvAuthor.setText(TextUtils.isEmpty(book.getAuthor()) ?
                    book.getAuthor2() :
                    book.getAuthor());

            mImageViewTypeBook.setImageResource(book.getFieldSet().equals("11") ?
                    R.drawable.ondemand_icon :
                    R.drawable.disc_icon);

            if (book.isAddedToQueue()) {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText(R.string.book_added_to_queue);
            } else {
                if (book.isPreviouslyRented()) {
                    tvStatus.setVisibility(View.VISIBLE);
                    tvStatus.setText(R.string.book_previously_rented);
                } else {
                    tvStatus.setVisibility(View.GONE);
                }
            }

            if (!isStatusLoaded)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        }
    }

    private class ViewHolderPendingFooter extends RecyclerView.ViewHolder {

        ViewHolderPendingFooter(View itemView) {
            super(itemView);
        }

        private void bind(boolean visible) {
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            getItemList() == null || getItemList().size() == 0 ?
                                    0 :
                                    visible ? ViewGroup.LayoutParams.WRAP_CONTENT : 0);
            layoutParams.setFullSpan(true);
            itemView.setLayoutParams(layoutParams);
        }
    }
}