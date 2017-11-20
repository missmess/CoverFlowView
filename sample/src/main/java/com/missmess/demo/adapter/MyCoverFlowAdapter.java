package com.missmess.demo.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.missmess.coverflowview.ACoverFlowAdapter;
import com.missmess.demo.R;

/**
 * @author wl
 * @since 2016/07/28 16:52
 */
public class MyCoverFlowAdapter extends ACoverFlowAdapter<ACoverFlowAdapter.ViewHolder> {
    public static final int[] InitialTips = {R.string.cover_tip0, R.string.cover_tip1, R.string.cover_tip2, R.string.cover_tip3, R.string.cover_tip4};
    public static final int[] NewTips = {R.string.new_cover_tip0, R.string.new_cover_tip1, R.string.new_cover_tip2, R.string.new_cover_tip3, R.string.new_cover_tip4, R.string.app_name};
    private int[] tipRess = InitialTips;

    public void setTips(int[] tips) {
        this.tipRess = tips;
    }

    public int[] getTips() {
        return tipRess;
    }

    @Override
    public int getCount() {
        return tipRess.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View content;
        if(type == 0) {
            content = View.inflate(parent.getContext(), R.layout.item_coverflow1, new LinearLayout(parent.getContext()));
            return new ViewHolder1(content);
        } else if(type == 1) {
            content = View.inflate(parent.getContext(), R.layout.item_coverflow2, new LinearLayout(parent.getContext()));
            return new ViewHolder2(content);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ACoverFlowAdapter.ViewHolder vh, int position) {
        if(vh instanceof ViewHolder1) {
            ViewHolder1 holder = (ViewHolder1) vh;
            holder.textView.setText(tipRess[position]);
            if(position == 2) {
                holder.fl.setVisibility(View.VISIBLE);
                holder.textView1.setText("0%");
                holder.progressBar.setProgress(0);
            } else {
                holder.fl.setVisibility(View.GONE);
            }
        } else if(vh instanceof ViewHolder2) {
            ViewHolder2 holder = (ViewHolder2) vh;
            final Context context = holder.textView2.getContext();
            holder.textView2.setText(context.getString(R.string.circle, position));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了item上的button", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 4)
            return 1;
        return 0;
    }

    public void animProgress(View itemView) {
        final ViewHolder1 holder = (ViewHolder1) itemView.getTag();
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int va = (int) animation.getAnimatedValue();
                holder.progressBar.setProgress(va);
                holder.textView1.setText(String.format("%d%%", va));
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    class ViewHolder1 extends ACoverFlowAdapter.ViewHolder {
        TextView textView;
        View fl;
        TextView textView1;
        ProgressBar progressBar;

        public ViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            fl = itemView.findViewById(R.id.fl);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    class ViewHolder2 extends ACoverFlowAdapter.ViewHolder {
        TextView textView2;
        ImageButton imageView;

        public ViewHolder2(View itemView) {
            super(itemView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView = (ImageButton) itemView.findViewById(R.id.imageView);
        }
    }
}
