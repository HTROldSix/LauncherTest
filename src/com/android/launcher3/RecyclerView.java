package com.android.launcher3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by qi.tao on 2017/1/6.
 */
public class RecyclerView extends android.support.v7.widget.RecyclerView implements View.OnLongClickListener{

    private Context mContext;
    private Launcher mLauncher;

    public RecyclerView(Context context, Launcher launcher) {
        super(context);
        mContext = context;
        mLauncher = launcher;
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        Log.i("ViewPaper", "x - " + ev.getX() + " - y - " + ev.getY());
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.i("ViewPaper", "onLongClick");
        return false;
    }
//
//    @Override
//    public boolean supportsFlingToDelete() {
//        return true;
//    }
//
//    @Override
//    public boolean supportsAppInfoDropTarget() {
//        return true;
//    }
//
//    @Override
//    public boolean supportsDeleteDropTarget() {
//        return false;
//    }
//
//    @Override
//    public float getIntrinsicIconScaleFactor() {
//        DeviceProfile grid = mLauncher.getDeviceProfile();
//        return (float) grid.allAppsIconSizePx / grid.iconSizePx;
//    }
//
//    @Override
//    public void onFlingToDeleteCompleted() {
//        // We just dismiss the drag when we fling, so cleanup here
//        mLauncher.exitSpringLoadedDragModeDelayed(true,
//                Launcher.EXIT_SPRINGLOADED_MODE_SHORT_TIMEOUT, null);
//        mLauncher.unlockScreenOrientation(false);
//    }
//
//    @Override
//    public void onDropCompleted(View target, DropTarget.DragObject d, boolean isFlingToDelete, boolean success) {
//    }
}