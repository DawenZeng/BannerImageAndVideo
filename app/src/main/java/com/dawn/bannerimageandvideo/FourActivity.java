package com.dawn.bannerimageandvideo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dawn.bannerimageandvideo.adapter.BannerViewAdapter;
import com.dawn.bannerimageandvideo.bean.BannerModel;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {

    public ViewPager mViewPager;
    private LinearLayout mLinearLayoutDot;
    private List<BannerModel> list;
    private BannerViewAdapter mAdapter;
    private List<ImageView> mImageViewDotList = new ArrayList();

    private int currentPosition = 0;
    private int dotPosition = 0;
    private int prePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        mViewPager = findViewById(R.id.vp_main);
        mLinearLayoutDot = findViewById(R.id.ll_main_dot);

        initData();

        setDot();

        autoBanner();

        autoPlay();
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
    }

    private void autoBanner() {
        mAdapter = new BannerViewAdapter(this, list);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(currentPosition);
        //页面改变监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {    //判断当切换到第0个页面时把currentPosition设置为images.length,即倒数第二个位置，小圆点位置为length-1
                    currentPosition = list.size();
                    dotPosition = list.size() - 1;
                } else if (position == list.size() + 1) {    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
                    currentPosition = 1;
                    dotPosition = 0;
                } else {
                    currentPosition = position;
                    dotPosition = position - 1;
                }
                //  把之前的小圆点设置背景为暗红，当前小圆点设置为红色
                mImageViewDotList.get(prePosition).setBackgroundResource(R.drawable.red_dot_night);
                mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
                prePosition = dotPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当state为SCROLL_STATE_IDLE即没有滑动的状态时切换页面
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mViewPager.setCurrentItem(currentPosition, false);
                }
            }
        });
    }

    //  设置轮播小圆点
    private void setDot() {
        //  设置LinearLayout的子控件的宽高，这里单位是像素。
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        params.rightMargin = 20;
        //  for循环创建images.length个ImageView（小圆点）
        for (int i = 0; i < list.size(); i++) {
            ImageView imageViewDot = new ImageView(this);
            imageViewDot.setLayoutParams(params);
            //  设置小圆点的背景为暗红图片
            imageViewDot.setBackgroundResource(R.drawable.red_dot_night);
            mLinearLayoutDot.addView(imageViewDot);
            mImageViewDotList.add(imageViewDot);
        }
        //设置第一个小圆点图片背景为红色
        mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
    }

    //  设置自动播放
    private void autoPlay() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    SystemClock.sleep(3000);
                    currentPosition++;
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mViewPager.setCurrentItem(currentPosition, false);
            }
        }
    };
}
