package com.paging.pagingadapter;

import android.support.v7.widget.GridLayoutManager;

import static com.paging.pagingadapter.AbstractPagingAdapter.LOADING_VIEW_TYPE;

public class PagingSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private AbstractPagingAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public PagingSpanSizeLookup(AbstractPagingAdapter adapter, GridLayoutManager manager) {
        mAdapter = adapter;
        mLayoutManager = manager;
    }

    @Override
    public int getSpanSize(int position) {
        if (mAdapter.getItemViewType(position) == LOADING_VIEW_TYPE)
            return mLayoutManager.getSpanCount();
        else
            return 1;
    }
}
