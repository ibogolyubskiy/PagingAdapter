package com.paging.pagingadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PagingGridLayoutManager extends GridLayoutManager {

    public PagingGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        super.onAdapterChanged(oldAdapter, newAdapter);
        if (newAdapter != null)
            setSpanSizeLookup(new PagingSpanSizeLookup(newAdapter, this));
    }
}
