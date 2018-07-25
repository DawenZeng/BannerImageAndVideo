package com.dawn.bannerimageandvideo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dawn.bannerimageandvideo.adapter.BannerViewAdapter;
import com.dawn.bannerimageandvideo.bean.BannerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TwoActivity extends AppCompatActivity {

    private static final int UPTATE_VIEWPAGER = 0;
    public ViewPager mViewPager;
    private List<BannerModel> list;
    private BannerViewAdapter mAdapter;
    private int autoCurrIndex = 0;//设置当前 第几个图片 被选中
    private Timer timer;
    private TimerTask timerTask;
    private long period = 5000;//轮播图展示时长,默认5秒

    //定时轮播图片，需要在主线程里面修改 UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_VIEWPAGER:
                    if (msg.arg1 != 0) {
                        mViewPager.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页时，不显示翻页动画效果，
                        mViewPager.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mViewPager = findViewById(R.id.pager);
        initData();
    }

    /**
     * 广告轮播图测试数据
     */
    public void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BannerModel listBean = new BannerModel();
            if (i == 1 || i == 3) {
                listBean.setBannerName("动画片");
                listBean.setBannerUrl("https://erp.vipstation.com.hk/images/IC/IC0002851/IC0002851_180419124311770_iOS.mp4");
                listBean.setPlayTime(27000);
                listBean.setUrlType(1);//图片类型 视频
                list.add(listBean);
            } else {
                listBean.setBannerName("广告");
                listBean.setBannerUrl("http://pic11.nipic.com/20101201/4452735_182232064453_2.jpg");
                listBean.setPlayTime(2000);
                listBean.setUrlType(0);//图片类型 图片
                list.add(listBean);
            }
        }

        period = list.get(0).getPlayTime();
        autoBanner();

    }

    private void autoBanner() {
        mViewPager.setOffscreenPageLimit(0);
        mAdapter = new BannerViewAdapter(this, list);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                autoCurrIndex = position;//动态设定轮播图每一页的停留时间
                period = list.get(position).getPlayTime();
                if (timer != null) {//每次改变都需要重新创建定时器
                    timer.cancel();
                    timer = null;
                    timer = new Timer();
                    if (timerTask != null) {
                        timerTask.cancel();
                        timerTask = null;
                        createTimerTask();
                    }
                    timer.schedule(timerTask, period, period);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        createTimerTask();//创建定时器

        timer = new Timer();
        timer.schedule(timerTask, 5000, period);

    }


    public void createTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_VIEWPAGER;
                if (autoCurrIndex == list.size() - 1) {
                    autoCurrIndex = -1;
                }
                message.arg1 = autoCurrIndex + 1;
                mHandler.sendMessage(message);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
