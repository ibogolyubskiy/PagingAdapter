package com.paging.pagingadapterdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.paging.pagingadapter.PagingListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements PagingListener, Runnable {

    protected RecyclerView recyclerView;
    protected SampleAdapter adapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        recyclerView = findViewById(R.id.my_recycler_view);
        adapter = new SampleAdapter();
        recyclerView.setLayoutManager(provideLayoutManager());
        recyclerView.setAdapter(adapter);
    }

    protected abstract RecyclerView.LayoutManager provideLayoutManager();

    @Override
    public void onLoadMore() {
        handler.postDelayed(this, 5000);
    }

    @Override
    public void run() {
        List<String> subData = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            subData.add(Integer.toString(100 - (adapter.getPagedItemCount() + i)));
        }
        adapter.addAll(subData);
        adapter.setHasMoreData(adapter.getPagedItemCount() < 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.add(String.valueOf(adapter.getPagedItemCount() + 1));
                break;
            case R.id.remove:
                adapter.remove(0);
                break;
            case R.id.replace:
                adapter.replace(adapter.getItems().indexOf("4"), String.valueOf(adapter.getPagedItemCount()));
                break;
            case R.id.clear:
                adapter.clear();
                break;
            case R.id.insert:
                adapter.insert(1, String.valueOf(adapter.getPagedItemCount() + 1));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void start(View v) {
        adapter.setPagingListener(this);
    }

    public void stop(View v) {
        adapter.setPagingListener(null);
        handler.removeCallbacks(this);
    }
}

