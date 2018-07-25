package com.dawn.bannerimageandvideo;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dawn.videoplayerlibrary.JZVideoPlayer;
import com.dawn.videoplayerlibrary.JZVideoPlayerStandard;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ThreeActivity extends AppCompatActivity {

    private List<String> bannerList = new ArrayList<>();
    private JZVideoPlayerStandard jcVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        bannerList = (List<String>) getIntent().getStringArrayListExtra("list");
        jcVideo = findViewById(R.id.jc_video);
        //获取视频第一帧的图片
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        if (Build.VERSION.SDK_INT >= 14) {
            media.setDataSource(bannerList.get(0), new HashMap<String, String>());
        } else {
            media.setDataSource(bannerList.get(0));
        }
        //获取第一帧
        Bitmap bitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        media.release();
        //用于Glide可以加载Bitmap图片
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        jcVideo.setUp(bannerList.get(0), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(myJZVideoPlayerStandard.thumbImageView);
        Glide.with(this).load(bytes).into(jcVideo.thumbImageView);
//        JZVideoPlayer.setJzUserAction(null);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JZVideoPlayer.setJzUserAction(null);
        JZVideoPlayer.releaseAllVideos();
//        JZVideoPlayerStandard.setOnClickFullScreenListener(null);
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
