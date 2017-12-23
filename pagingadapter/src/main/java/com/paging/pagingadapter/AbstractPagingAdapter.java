package com.paging.pagingadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public abstract class AbstractPagingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int NORMAL_VIEW_TYPE = 0;
    public final static int LOADING_VIEW_TYPE = 0x70000000;

    /*
    Proxy for getItemCount. Must return the current ammount of mItems in the RecyclerView
     */
    public abstract int getPagedItemCount();
    /*
    Proxy for getItemViewType. Must return the current type for a certain position
     */
    public int getPagedItemViewType(int position) {
        return 0;
    }

    /*
    Proxy for onCreateViewHolder for normal mItems. Must initialize the view, and return a PagedViewHolder or a subclass.
     */
    protected abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);
    /*
    Proxy for onBindViewHolder for normal mItems. Must bind the data in the viewHolder for a certain position
     */
    protected abstract void onBindHolder(RecyclerView.ViewHolder viewHolder, int position);


    public boolean isLoading() {
        return mIsLoading;
    }

    private boolean mIsLoading;
    private PagingListener mPagingListener;
    private boolean mHasMoreData;

    public void setPagingListener(PagingListener pagingListener) {
        mPagingListener = pagingListener;
        setHasMoreData(pagingListener != null);

    }

    public boolean hasMoreData() {
        return mHasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        mIsLoading = false;
        mHasMoreData = hasMoreData;
        notifyDataSetChanged();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType >= NORMAL_VIEW_TYPE && viewType < LOADING_VIEW_TYPE) {
            return onCreateHolder(parent, viewType - NORMAL_VIEW_TYPE);
        } else {
            return onCreateLoadingHolder(parent, viewType - LOADING_VIEW_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hasMoreData() && position >= getPagedItemCount()) {

            if (!mIsLoading) {
                mIsLoading = true;
                if (mPagingListener != null) {
                    mPagingListener.onLoadMore();
                }
            }
            onBindLoadingHolder(holder);
        } else {
            onBindHolder(holder, position);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (hasMoreData() && position >= getPagedItemCount()) {
            return getPagedItemViewType(position) + LOADING_VIEW_TYPE;
        } else {
            return getPagedItemViewType(position) + NORMAL_VIEW_TYPE;
        }
    }

    @Override
    public final int getItemCount() {
        return getPagedItemCount() + (hasMoreData() ? 1 : 0);
    }


    protected int getLoadingLayout() {
        return R.layout.view_basic_loading;
    }

    protected RecyclerView.ViewHolder onCreateLoadingHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), getLoadingLayout(), null);
        view.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        return new LoadingViewHolder(view);
    }

    protected void onBindLoadingHolder(RecyclerView.ViewHolder holder) { }
}