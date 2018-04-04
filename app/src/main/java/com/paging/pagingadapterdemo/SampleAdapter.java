package com.paging.pagingadapterdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paging.pagingadapter.PagingAdapter;

public class SampleAdapter extends PagingAdapter<String> {

    public SampleAdapter() {
        super();
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getItemViewType(position) == NORMAL_VIEW_TYPE ? getItem(position).hashCode() : -1;
    }

    @Override
    protected SampleHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.text_view_layout, null);
        return new SampleHolder(view);
    }

    @Override
    protected void onBindHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SampleHolder holder = (SampleHolder) viewHolder;
        holder.textView.setText(mItems.get(position));
    }

    private class SampleHolder extends RecyclerView.ViewHolder {

        TextView textView;

        SampleHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}