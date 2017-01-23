package com.android.launcher3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by qi.tao on 2017/1/6.
 */
public class RecyclerView extends android.support.v7.widget.RecyclerView {

    public RecyclerView(Context context, Launcher launcher) {
        super(context);
    }

    public RecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}