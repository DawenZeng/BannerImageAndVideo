package com.dawn.bannerimageandvideo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dawn.bannerimageandvideo.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {


    private List<String> bannerList = new ArrayList<>();
    private ViewPager banner;
    BannerAdapter bannerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        bannerList = (List<String>) getIntent().getStringArrayListExtra("list");
        banner = findViewById(R.id.banner);
//        banner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        bannerAdapter = new BannerAdapter(this);
        bannerAdapter.update(bannerList);
        banner.setAdapter(bannerAdapter);
    }
}
