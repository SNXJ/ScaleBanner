package com.shing.scalebanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shing.scalelibrary.CenterSnapHelper;
import com.shing.scalelibrary.ScaleLayoutManager;
import com.shing.scalelibrary.ViewPagerLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private ScaleLayoutManager scaleLayoutManager;
//    private float x = 0;
//    private float y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mList.add(R.mipmap.home_banner_1);
        mList.add(R.mipmap.home_banner_2);
        mList.add(R.mipmap.home_banner_3);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        scaleLayoutManager = new ScaleLayoutManager(this, ScreenUtil.dip2px(this, 10));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(scaleLayoutManager);

        initLayoutManger();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: =======" + position);
            }
        });
//itemClick被抢占解决  抛弃此方法
//        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
////                Log.d(TAG, "onClick: =======ACTION_DOWN======x=");
//
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        x = motionEvent.getX();
//                        y = motionEvent.getY();
////                        Log.d(TAG, "onClick: =======ACTION_DOWN======x=" + x + "==y==" + y);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        x = motionEvent.getX() - x;
//                        y = motionEvent.getY() - y;
////                        Log.d(TAG, "onClick: =======ACTION_UP=======x=" + x + "==y==" + y);
//                        break;
//                }
//                if (Math.abs(x) < 5 && Math.abs(y) < 5) {
//                    Log.d(TAG, "onClick: =======ACTION_gooooooogo=======position=" + mPosition);
//                }
//
//                return false;
//            }
//        });
//
    }


    private void initLayoutManger() {
        // 支持Builder方式
//        scaleLayoutManager= new  ScaleLayoutManager.Builder(ScaleBanner2Activity.this,10).build();

//        scaleLayoutManager.setMaxVisibleItemCount(3);
//        scaleLayoutManager.setItemSpace(5);//间隔
        scaleLayoutManager.setCenterScale(1.2f);//中心view放大

        scaleLayoutManager.setMoveSpeed(0.8f);//速率
        scaleLayoutManager.setOrientation(ViewPagerLayoutManager.HORIZONTAL);//方向
        scaleLayoutManager.setMaxAlpha(1.0f);//透明度
        scaleLayoutManager.setMinAlpha(0.8f);
        scaleLayoutManager.setReverseLayout(false);//翻转
        scaleLayoutManager.setInfinite(true);//无限轮播

        CenterSnapHelper centerSnapHelper = new CenterSnapHelper();//居中
        centerSnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置

//        AutoPlaySnapHelper autoPlaySnapHelper = new AutoPlaySnapHelper(AutoPlaySnapHelper.TIME_INTERVAL, AutoPlaySnapHelper.RIGHT);//间隔 和方向     自动轮播
//        autoPlaySnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置

        scaleLayoutManager.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // mPosition = position;
                //do something
                //可自定义指示器
                Log.i(TAG, "PageSelected=" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
