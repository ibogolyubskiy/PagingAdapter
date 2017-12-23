package com.paging.pagingadapterdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.paging.pagingadapter.PagingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
This file is part of PagedRecyclerView

PagedRecyclerView is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

public class MainActivity extends AppCompatActivity implements PagingListener {

    RecyclerView recyclerView;
    SampleAdapter adapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SampleAdapter();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!adapter.isLoading()) return;
                List<String> subData = new ArrayList<>();
                for (int i = 0; i<10; i++) {
                    subData.add(Integer.toString(100 - (adapter.getPagedItemCount() + i)));
                }
                Collections.sort(subData, new Comparator<String>() {
                    @Override
                    public int compare(String s, String t1) {
                        return Integer.valueOf(s).compareTo(Integer.valueOf(t1));
                    }
                });
                adapter.addAll(subData);
                adapter.setHasMoreData(adapter.getPagedItemCount() < 100);
            }
        }, 5000);
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
    }
}
