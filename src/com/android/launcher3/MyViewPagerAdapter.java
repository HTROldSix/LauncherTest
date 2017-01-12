package com.android.launcher3;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    ViewPager v;

    public MyViewPagerAdapter(Context context, Map<String, List<AppInfo>> allAppsTypeMap, List<String> name,
                              View.OnTouchListener touchListener,
                              View.OnClickListener iconClickListener, Launcher mLauncher) {
        mContext = context;
        size = allAppsTypeMap.size();
        try {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            RecyclerView r = new RecyclerView(context, mLauncher);
            r.setLayoutManager(gridLayoutManager);
            MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(0)), touchListener, iconClickListener);
            r.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc = new MyItemTouchHelper();
            itc.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(0)));
            new ItemTouchHelper(itc).attachToRecyclerView(r);
            viewLists.add(r);
            RecyclerView r1 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r1.setLayoutManager(gridLayoutManager1);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(1)), touchListener, iconClickListener);
            r1.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc1 = new MyItemTouchHelper();
            itc1.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(1)));
            new ItemTouchHelper(itc1).attachToRecyclerView(r1);
            viewLists.add(r1);
            RecyclerView r2 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r2.setLayoutManager(gridLayoutManager2);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(2)), touchListener, iconClickListener);
            r2.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc2 = new MyItemTouchHelper();
            itc2.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(2)));
            new ItemTouchHelper(itc2).attachToRecyclerView(r2);
            viewLists.add(r2);
            RecyclerView r3 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager3 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager3.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r3.setLayoutManager(gridLayoutManager3);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(3)), touchListener, iconClickListener);
            r3.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc3 = new MyItemTouchHelper();
            itc3.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(3)));
            new ItemTouchHelper(itc3).attachToRecyclerView(r3);
            viewLists.add(r3);
            RecyclerView r4 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager4 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager4.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r4.setLayoutManager(gridLayoutManager4);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(4)), touchListener, iconClickListener);
            r4.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc4 = new MyItemTouchHelper();
            itc4.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(4)));
            new ItemTouchHelper(itc4).attachToRecyclerView(r4);
            viewLists.add(r4);
            RecyclerView r5 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager5 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager5.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r5.setLayoutManager(gridLayoutManager5);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(5)), touchListener, iconClickListener);
            r5.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc5 = new MyItemTouchHelper();
            itc5.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(5)));
            new ItemTouchHelper(itc5).attachToRecyclerView(r5);
            viewLists.add(r5);
            RecyclerView r6 = new RecyclerView(context, mLauncher);
            GridLayoutManager gridLayoutManager6 = new GridLayoutManager(context, columnsNumber);
            gridLayoutManager6.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? columnsNumber : 1;
                }
            });
            r6.setLayoutManager(gridLayoutManager6);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, allAppsTypeMap.get(name.get(6)), touchListener, iconClickListener);
            r6.setAdapter(myRecyclerViewAdapter);
            MyItemTouchHelper itc6 = new MyItemTouchHelper();
            itc6.init(myRecyclerViewAdapter, allAppsTypeMap.get(name.get(6)));
            new ItemTouchHelper(itc6).attachToRecyclerView(r6);
            viewLists.add(r6);
        } catch (IndexOutOfBoundsException e) {
            Log.e("Launcher.AppClassify", "MyViewPagerAdapter IndexOutOfBoundsException e " + e);
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
        v = (ViewPager) container;
        v.addView(viewLists.get(position), 0);
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(viewLists.get(position));
    }

    class MyItemTouchHelper extends ItemTouchHelper.Callback {
        private android.support.v7.widget.RecyclerView.ViewHolder vh;
        android.support.v7.widget.RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> myRecyclerViewAdapter;
        List<AppInfo> list;

        public void init(android.support.v7.widget.RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> myRecyclerViewAdapter, List<AppInfo> list) {
            this.myRecyclerViewAdapter = myRecyclerViewAdapter;
            this.list = list;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public int getMovementFlags(android.support.v7.widget.RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder
                viewHolder) {
            // 拖拽的标记，这里允许上下左右四个方向
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT;
            // 滑动的标记，这里允许左右滑动
//            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            int swipeFlags = 0;
            int i = makeMovementFlags(dragFlags, swipeFlags);
            return i;
        }

        /*
            这个方法会在某个Item被拖动和移动的时候回调，这里我们用来播放动画，当viewHolder不为空时为选中状态
            否则为释放状态
         */
        @Override
        public void onSelectedChanged(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int actionState) {
//            if (viewHolder != null && viewHolder.itemView != null) viewHolder.itemView.setVisibility(View.INVISIBLE);
            if (v != null) {
                MyRecyclerViewAdapter.visibility = actionState == 2;
                if (MyRecyclerViewAdapter.visibility) {
                    v.setInvalidate(true);//ACTION_DOWN 2  ACTION_UP 0
                    myRecyclerViewAdapter.notifyItemChanged(0);
                } else {
                    myRecyclerViewAdapter.notifyItemChanged(0);
                    v.setInvalidate(false);//ACTION_DOWN 2  ACTION_UP 0
                }
            }
            super.onSelectedChanged(viewHolder, actionState);
        }


        @Override
        public boolean onMove(android.support.v7.widget.RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder viewHolder,
                              android.support.v7.widget.RecyclerView.ViewHolder target) {
            if (viewHolder.getAdapterPosition() == 0 || target.getAdapterPosition() == 0)
                return false;
            // 移动时更改列表中对应的位置并返回true
            Collections.swap(list, viewHolder.getAdapterPosition() - 1, target
                    .getAdapterPosition() - 1);
            return true;
        }

        /*
            当onMove返回true时调用
         */
        @Override
        public void onMoved(android.support.v7.widget.RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int
                fromPos, android.support.v7.widget.RecyclerView.ViewHolder target, int toPos, int x, int y) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            // 移动完成后刷新列表
            myRecyclerViewAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target
                    .getAdapterPosition());
        }

        @Override
        public void onSwiped(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int direction) {
//            // 将数据集中的数据移除
//            list.remove(viewHolder.getAdapterPosition());
//            // 刷新列表
//            myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }
}
