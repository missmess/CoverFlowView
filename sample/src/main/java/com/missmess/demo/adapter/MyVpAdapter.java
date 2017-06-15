package com.missmess.demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.missmess.coverflowview.CoverFlowView;
import com.missmess.demo.R;

/**
 * @author wl
 * @since 2016/07/28 17:01
 */
public class MyVpAdapter extends PagerAdapter {
    private CoverFlowView mCoverFlowView;

    public  MyVpAdapter(CoverFlowView coverFlowView) {
        this.mCoverFlowView = coverFlowView;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "Introduction";
                break;
            case 1:
                title = "Switch Method";
                break;
            case 2:
                title = "Change Data";
                break;
        }
        return title;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View content = null;
        switch (position) {
            case 0:
                content = View.inflate(container.getContext(), R.layout.vp_layout1, null);
                break;
            case 1:
                content = View.inflate(container.getContext(), R.layout.vp_layout2, null);
                Switch switch1 = (Switch) content.findViewById(R.id.switch1);
                switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mCoverFlowView.setClick2SwitchEnabled(isChecked);
                    }
                });
                Switch switch2 = (Switch) content.findViewById(R.id.switch2);
                switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mCoverFlowView.setLoopMode(isChecked);
                    }
                });
                break;
            case 2:
                content = View.inflate(container.getContext(), R.layout.vp_layout3, null);
                break;
        }
        container.addView(content);
        return content;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
