package com.android.launcher3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.launcher3.allapps.AlphabeticalAppsList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qi.tao on 2016/12/19.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<AppInfo> mApps;
    private PackageManager packageManager;
    private OnClickListener onClickListener;

    public interface OnClickListener{
        public void onClick();
    }

    public void setOnClickListener(OnClickListener onClickListener){
       this.onClickListener = onClickListener;
    }

    public MyRecyclerViewAdapter(Context context, List<AppInfo> mApps) {
        this.mApps = mApps;
        packageManager = context.getPackageManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onClick();
                }
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setmTxt(mApps.get(position));
    }

    @Override
    public int getItemCount() {
        int i = mApps.size();
        return i;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxt;

        public ViewHolder(View view) {
            super(view);
            mTxt = (TextView) view.findViewById(R.id.textView);
        }

        void setmTxt(AppInfo appInfo) {
            Drawable drawable = new BitmapDrawable(appInfo.iconBitmap);
            drawable.setBounds(0, 0, Launcher.mDeviceProfile.iconSizePx, Launcher.mDeviceProfile.iconSizePx);
            mTxt.setCompoundDrawables(null, drawable, null, null);
            mTxt.setText(appInfo.title);
        }
    }
}
