package com.dawn.videoplayerlibrary.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.dawn.videoplayerlibrary.JZVideoPlayerStandard;


/**
 * Created by Nathen on 2016/11/6.
 */

public class JZVideoPlayerStandardShowTextureViewAfterAutoComplete extends JZVideoPlayerStandard {
    public JZVideoPlayerStandardShowTextureViewAfterAutoComplete(Context context) {
        super(context);
    }

    public JZVideoPlayerStandardShowTextureViewAfterAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        thumbImageView.setVisibility(View.GONE);
    }

}
