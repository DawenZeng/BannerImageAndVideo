package com.dawn.bannerimageandvideo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dawn.bannerimageandvideo.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private List<String> bannerList = new ArrayList<>();
    private ViewPager banner;
    private LinearLayout group;
    BannerAdapter bannerAdapter;
    private ListView listview;
    private String[] presidents = {"北京", "深圳", "济南", "广州", "海南", "香港", "澳门", "aa", "北京", "深圳", "济南", "广州", "海南", "香港", "澳门", "aa", "北京", "深圳", "济南", "广州", "海南", "香港", "澳门", "aa", "北京", "深圳", "济南", "广州", "海南", "香港", "澳门", "aa"};

    private int mIndicatorSelectedResId = R.drawable.radiu_blue;//蓝色导航点
    private int mIndicatorUnselectedResId = R.drawable.radiu_gray;//灰色导航点
    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        bannerList = (List<String>) getIntent().getStringArrayListExtra("list");
        banner = findViewById(R.id.vp_main);
        group = findViewById(R.id.ll_main_dot);
        listview = findViewById(R.id.list);
        //  设置LinearLayout的子控件的宽高，这里单位是像素。
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        params.rightMargin = 20;
        // 将点点加入到ViewGroup中
        tips = new ImageView[bannerList.size()];
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setImageResource(mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(mIndicatorUnselectedResId);
            }
            tips[i] = imageView;
            group.addView(imageView);
        }

        bannerAdapter = new BannerAdapter(this);
        bannerAdapter.update(bannerList);
        banner.setAdapter(bannerAdapter);
        // 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        // viewPager.setCurrentItem((mImageViews.length) * 100);
        // 一开始不能往滑，但没有图片是默认图片一开始就显示
        banner.setCurrentItem(0);
        // 设置监听，主要是设置点点的背景
        banner.setOnPageChangeListener(this);

        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, presidents));
        listview.setFocusable(false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % bannerList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setImageResource(mIndicatorSelectedResId);
            } else {
                tips[i].setImageResource(mIndicatorUnselectedResId);
            }
        }
    }
}
