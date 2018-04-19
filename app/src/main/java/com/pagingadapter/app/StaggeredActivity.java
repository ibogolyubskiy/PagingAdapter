package com.pagingadapter.app;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class StaggeredActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }
}
