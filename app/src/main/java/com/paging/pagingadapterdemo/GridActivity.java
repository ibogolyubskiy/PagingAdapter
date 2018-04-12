package com.paging.pagingadapterdemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.paging.pagingadapter.PagingGridLayoutManager;
import com.paging.pagingadapter.PagingSpanSizeLookup;

public class GridActivity extends BaseActivity {

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new PagingGridLayoutManager(this, 4);
    }
}
