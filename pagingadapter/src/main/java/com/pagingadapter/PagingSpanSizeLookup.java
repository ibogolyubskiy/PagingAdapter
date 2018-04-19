package com.pagingadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import static com.pagingadapter.AbstractPagingAdapter.LOADING_VIEW_TYPE;

public class PagingSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public PagingSpanSizeLookup(RecyclerView.Adapter adapter, GridLayoutManager manager) {
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
