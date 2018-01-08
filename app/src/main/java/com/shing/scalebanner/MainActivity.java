package com.shing.scalebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.shing.scalelibrary.AutoPlaySnapHelper;
import com.shing.scalelibrary.ScaleLayoutManager;
import com.shing.scalelibrary.ViewPagerLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private ScaleLayoutManager scaleLayoutManager;

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


        mRecyclerView.setAdapter(new RecyclerViewAdapter(mList));
        mRecyclerView.setLayoutManager(scaleLayoutManager);
        initLayoutManger();


    }

    private void initLayoutManger() {
//        scaleLayoutManager= new  ScaleLayoutManager.Builder(ScaleBanner2Activity.this,10).build();

//        scaleLayoutManager.setMaxVisibleItemCount(1);
        scaleLayoutManager.setItemSpace(5);
        scaleLayoutManager.setCenterScale(1.2f);
//        scaleLayoutManager.setMoveSpeed(1.0f);//速率
        scaleLayoutManager.setOrientation(ViewPagerLayoutManager.HORIZONTAL);
        scaleLayoutManager.setMaxAlpha(1.0f);//透明度
        scaleLayoutManager.setMinAlpha(1.0f);
        scaleLayoutManager.setReverseLayout(false);//翻转
        scaleLayoutManager.setInfinite(true);//无限轮播
//        CenterSnapHelper centerSnapHelper = new CenterSnapHelper();
//      centerSnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置

        AutoPlaySnapHelper autoPlaySnapHelper= new AutoPlaySnapHelper(AutoPlaySnapHelper.TIME_INTERVAL,AutoPlaySnapHelper.RIGHT);//间隔 和方向     自动轮播

        autoPlaySnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置

        scaleLayoutManager.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

//                LogUtils.i("+++++++++++++","++++++++++++"+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
