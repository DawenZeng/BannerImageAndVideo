package com.dawn.bannerimageandvideo.qsvideo.listvideo;

import android.view.View;

public interface Getter {
    View getChildAt(int position);

    int indexOfChild(View view);

    int getChildCount();

    int getLastVisiblePosition();

    int getFirstVisiblePosition();
}
