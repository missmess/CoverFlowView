package com.missmess.coverflowview;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ACoverFlowAdapter<T extends ACoverFlowAdapter.ViewHolder> {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    /**
     * CoverFlowView数据集发生改变，刷新，尽量保留状态且不影响滑动事件
     */
    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    /**
     * CoverFlowView重绘刷新。重置CoverFlowView的所有状态。
     */
    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
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
            Object tag = convertView.getTag();
            // 当前adapter的泛型类型T
            Type destType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            // 验证tag是否是T的实例，如果是，直接使用
            if (((Class) destType).isInstance(tag)) {
                holder = (T) tag;
                int cPos = holder.position;
                if(getItemViewType(cPos) == itemViewType) {
                    onBindViewHolder(holder, position);
                    return holder.getItemView();
                }
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
