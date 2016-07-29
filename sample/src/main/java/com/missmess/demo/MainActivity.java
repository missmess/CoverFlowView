package com.missmess.demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.missmess.coverflowview.CoverFlowView;
import com.missmess.demo.adapter.MyCoverFlowAdapter;
import com.missmess.demo.adapter.MyVpAdapter;
import com.missmess.demo.adapter.NewCoverFlowAdapter;


public class MainActivity extends AppCompatActivity {
    private CoverFlowView coverflow_view;
    private TabLayout tabl;
    private ViewPager vp;
    private MyCoverFlowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        coverflow_view = (CoverFlowView) findViewById(R.id.coverflow_view);
        tabl = (TabLayout) findViewById(R.id.tabl);
        vp = (ViewPager) findViewById(R.id.vp);

        adapter = new MyCoverFlowAdapter();
        coverflow_view.setAdapter(adapter);
        coverflow_view.setOnViewOnTopListener(new CoverFlowView.OnViewOnTopListener() {
            @Override
            public void viewOnTop(int position, View itemView) {
                if(position == 0) {
                    vp.setCurrentItem(1);
                }
            }
        });
        coverflow_view.setOnTopViewClickListner(new CoverFlowView.OnTopViewClickListner() {
            @Override
            public void onClick(int position, View itemView) {
                Toast.makeText(MainActivity.this, "position " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        coverflow_view.setOnTopViewLongClickListner(new CoverFlowView.OnTopViewLongClickListner() {
            @Override
            public void onLongClick(int position, View itemView) {
                Toast.makeText(MainActivity.this, "position " + position + " long clicked", Toast.LENGTH_SHORT).show();
            }
        });

        vp.setAdapter(new MyVpAdapter(coverflow_view));
        tabl.setupWithViewPager(vp);
    }

    public void vp2Clicker(View v) {
        switch (v.getId()) {
            case R.id.button:
                coverflow_view.gotoPrevious();
                break;
            case R.id.button2:
                coverflow_view.gotoForward();
                break;
        }
    }

    public void vp3Clicker(View v) {
        switch (v.getId()) {
            case R.id.button:
                if(adapter != null) {
                    adapter.setTips(new int[]{R.string.new_cover_tip0, R.string.new_cover_tip1, R.string.new_cover_tip2, R.string.new_cover_tip3, R.string.new_cover_tip4});
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.button2:
                if(adapter != null) {
                    coverflow_view.setAdapter(new NewCoverFlowAdapter());
                    adapter = null;
                } else {
                    adapter = new MyCoverFlowAdapter();
                    coverflow_view.setAdapter(adapter);
                }
                break;
            case R.id.button3:
                coverflow_view.setSelection(3, true);
                break;
            case R.id.button4:
                coverflow_view.setSelection(2, false);
                break;
        }
    }
}
