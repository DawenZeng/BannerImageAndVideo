package com.dawn.bannerimageandvideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 重新测量Listview的宽度和高度
 *
 */
public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 重新测量listview的高度
		int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
					MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, height);
	}

}
