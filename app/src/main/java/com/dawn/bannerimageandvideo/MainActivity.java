package com.dawn.bannerimageandvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

//实现视频+图片轮播
public class MainActivity extends AppCompatActivity {

    private Intent it;
    private int index;//获取视频的下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> bannerList = new ArrayList<>();
        bannerList.add("https://erp.vipstation.com.hk/images/itemimg/Q6053406.jpg");
        bannerList.add("https://erp.vipstation.com.hk/upload/image/20160527/20160527150822_4183.jpg");
        bannerList.add("https://erp.vipstation.com.hk/upload/image/20160527/20160527150824_3404.jpg");
        bannerList.add("https://erp.vipstation.com.hk/upload/image/20160527/20160527150825_1275.jpg");
        bannerList.add("https://erp.vipstation.com.hk/images/IC/IC0002851/IC0002851_180419124311770_iOS.mp4");
//        bannerList.add("http://videos.kpie.com.cn/videos/20170526/037DCE54-EECE-4520-AA92-E4002B1F29B0.mp4");

        for (int i = 0; i < bannerList.size(); i++) {
            if (bannerList.get(i).contains(".mp4"))
                index = i;
        }
        bannerList.add(0, bannerList.remove(index));//把视频放在第一个位置

        it = new Intent();
        it.putStringArrayListExtra("list", bannerList);
    }

    public void btn1(View view) {
        it.setClass(this, BannerActivity.class);
        startActivity(it);
    }

    public void btn2(View view) {
        it.setClass(this, TwoActivity.class);
        startActivity(it);
    }

    public void btn3(View view) {
        it.setClass(this, ThreeActivity.class);
        startActivity(it);
    }

    public void btn4(View view) {
        it.setClass(this, FourActivity.class);
        startActivity(it);
    }

    public void btn5(View view) {
        it.setClass(this, FiveActivity.class);
        startActivity(it);
    }

    public void btn6(View view) {
        it.setClass(this, SixActivity.class);
        startActivity(it);
    }

    public void btn7(View view) {
        it.setClass(this, SevenActivity.class);
        startActivity(it);
    }

    public void btn8(View view) {
        it.setClass(this, EightActivity.class);
        startActivity(it);
    }

    public void btn9(View view) {
        it.setClass(this, SharePreferenceSaveListActivity.class);
        startActivity(it);
    }
}
