package com.paging.pagingadapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PagingAdapter<T> extends AbstractPagingAdapter {

    protected List<T> mItems;

    public PagingAdapter() {
        mItems = new ArrayList<>();
    }

    public PagingAdapter(List<T> items) {
        mItems = items;
    }

    public void addAll(List<T> data) {
        int size = getPagedItemCount() + 1;
        mItems.addAll(data);
        notifyItemRangeInserted(size, mItems.size());
    }

    public void add(T item) {
        mItems.add(item);
        notifyItemInserted(getPagedItemCount() - 1);
    }

    public void insert(int position, T item) {
        if (outsideList(position)) return;
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (outsideList(position)) return;
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    private boolean outsideList(int position) {
        return position < 0 || position > getPagedItemCount() - 1;
    }

    public void remove(T item) {
        if (item == null) return;
        int position = mItems.indexOf(item);
        if (position == -1) return;
        remove(position);
    }

    public void replace(int position, T item) {
        if (outsideList(position)) return;
        mItems.set(position, item);
        notifyItemChanged(position);
    }

    public void clear() {
        int size = getPagedItemCount();
        mItems.clear();
        notifyItemRangeRemoved(0, size);
    }

    public T getItem(int position) {
        if (outsideList(position)) return null;
        return mItems.get(position);
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(mItems);
    }

    public void setItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getPagedItemCount() {
        return mItems.size();
    }
}
