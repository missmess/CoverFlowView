package com.missmess.demo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.missmess.coverflowview.ACoverFlowAdapter;
import com.missmess.demo.R;

/**
 * @author wl
 * @since 2016/07/28 18:23
 */
public class NewCoverFlowAdapter extends ACoverFlowAdapter<NewCoverFlowAdapter.ViewHolder> {

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View content = View.inflate(parent.getContext(), R.layout.item_newcoverflow, new LinearLayout(parent.getContext()));
        return new ViewHolder(content);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText("New Adapter Item " + position);
    }

    class ViewHolder extends ACoverFlowAdapter.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
