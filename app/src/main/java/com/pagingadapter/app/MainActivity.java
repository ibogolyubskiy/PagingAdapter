package com.pagingadapter.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linear(View view) {
        startActivity(new Intent(this, LinearActivity.class));
    }

    public void grid(View view) {
        startActivity(new Intent(this, GridActivity.class));
    }

    public void staggered(View view) {
        startActivity(new Intent(this, StaggeredActivity.class));
    }
}
