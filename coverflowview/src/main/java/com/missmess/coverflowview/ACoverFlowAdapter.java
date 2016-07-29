package com.missmess.coverflowview;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract class ACoverFlowAdapter<T extends ACoverFlowAdapter.ViewHolder> {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    protected View getView(int position, View convertView, ViewGroup parent) {
        T holder;
        int itemViewType = getItemViewType(position);
        if(convertView != null) {
            holder = (T) convertView.getTag();
            int cPos = holder.position;
            if(getItemViewType(cPos) == itemViewType) {
                onBindViewHolder(holder, position);
                return holder.getItemView();
            }
        }

        holder = onCreateViewHolder(parent, itemViewType);
        holder.position = position;
        holder.getItemView().setTag(holder);

        onBindViewHolder(holder, position);
        return holder.getItemView();
    }

    protected void refreshView(View view, int position) {
        T holder = (T) view.getTag();
        onBindViewHolder(holder, position);
    }

    public int getItemViewType(int position) {
        return 0;
    }

    /**
     * 总item数
     */
    public abstract int getCount();

    public abstract T onCreateViewHolder(ViewGroup parent, int type);

    public abstract void onBindViewHolder(T vh, int position);

    public static class ViewHolder {
        private View itemView;
        int position;
        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }
    }
}
