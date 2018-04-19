package com.pagingadapter.app;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LinearActivity extends BaseActivity {

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
    }
}
