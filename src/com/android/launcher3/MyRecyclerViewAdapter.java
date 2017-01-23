package com.android.launcher3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qi.tao on 2016/12/19.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<AppInfo> mApps;
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener mIconClickListener;

    public MyRecyclerViewAdapter(Context context, List<AppInfo> mApps, View.OnTouchListener touchListener, View.OnClickListener iconClickListener) {
        this.mApps = mApps;
        mLayoutInflater = LayoutInflater.from(context);
        mIconClickListener = iconClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = mLayoutInflater.inflate(R.layout.item_view, parent, false);
            return new ViewHolder(v);
        }
        BubbleTextView icon = (BubbleTextView) mLayoutInflater.inflate(
                R.layout.all_apps_icon, parent, false);
        icon.setPadding(0, 15, 0, 0);
        icon.setOnClickListener(mIconClickListener);
        return new ViewHolder(icon);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
//            TextView textView = (TextView) holder.mContent.findViewById(R.id.textView);
        } else {
            AppInfo info = mApps.get(position - 1);
            BubbleTextView icon = (BubbleTextView) holder.mContent;
            icon.applyFromApplicationInfo(info);
        }
    }

    @Override
    public int getItemCount() {
        int i = mApps.size();
        return i + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        return 2;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mContent;

        public ViewHolder(View view) {
            super(view);
            mContent = view;
        }
    }
}
