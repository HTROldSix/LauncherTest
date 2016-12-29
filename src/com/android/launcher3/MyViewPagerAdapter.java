package com.android.launcher3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.launcher3.allapps.AlphabeticalAppsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qi.tao on 2016/12/22.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<View> viewLists = new ArrayList<>();
    int columnsNumber = 4;
    int size;

    public MyViewPagerAdapter(Context context, Map<String, List<AppInfo>> allAppsTypeMap, List<String> name) {
        mContext = context;
        size = allAppsTypeMap.size();
        try {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, columnsNumber);
            RecyclerView r = new RecyclerView(context);
            r.setLayoutManager(gridLayoutManager);
            r.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(0))));
            viewLists.add(r);
            RecyclerView r1 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(context, columnsNumber);
            r1.setLayoutManager(gridLayoutManager1);
            r1.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(1))));
            viewLists.add(r1);
            RecyclerView r2 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(context, columnsNumber);
            r2.setLayoutManager(gridLayoutManager2);
            r2.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(2))));
            viewLists.add(r2);
            RecyclerView r3 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager3 = new GridLayoutManager(context, columnsNumber);
            r3.setLayoutManager(gridLayoutManager3);
            r3.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(3))));
            viewLists.add(r3);
            RecyclerView r4 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager4 = new GridLayoutManager(context, columnsNumber);
            r4.setLayoutManager(gridLayoutManager4);
            r4.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(4))));
            viewLists.add(r4);
            RecyclerView r5 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager5 = new GridLayoutManager(context, columnsNumber);
            r5.setLayoutManager(gridLayoutManager5);
            r5.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(5))));
            viewLists.add(r5);
            RecyclerView r6 = new RecyclerView(context);
            GridLayoutManager gridLayoutManager6 = new GridLayoutManager(context, columnsNumber);
            r6.setLayoutManager(gridLayoutManager6);
            r6.setAdapter(new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(6))));
            viewLists.add(r6);
        }catch (IndexOutOfBoundsException e){
            Log.e("Launcher.AppClassify","MyViewPagerAdapter IndexOutOfBoundsException e " + e);
        }
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(viewLists.get(position), 0);
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(viewLists.get(position));
    }
}
