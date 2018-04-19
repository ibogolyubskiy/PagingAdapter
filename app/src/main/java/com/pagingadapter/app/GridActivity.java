package com.pagingadapter.app;

import android.support.v7.widget.RecyclerView;

import com.pagingadapter.PagingGridLayoutManager;

public class GridActivity extends BaseActivity {

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new PagingGridLayoutManager(this, 4);
    }
}
