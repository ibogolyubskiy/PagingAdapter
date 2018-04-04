package com.paging.pagingadapterdemo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paging.pagingadapter.PagingSpanSizeLookup;

public class GridActivity extends BaseActivity {

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new PagingSpanSizeLookup(adapter, layoutManager));
        return layoutManager;
    }
}
