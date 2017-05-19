/*
* Copyright (C) 2014 MediaTek Inc.
* Modification based on code covered by the mentioned copyright
* and/or permission notice(s).
*/
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.android.launcher3.compat.LauncherActivityInfoCompat;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserHandleCompat;

import com.mediatek.launcher3.ext.LauncherLog;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Stores the list of all applications for the all apps view.
 */
public class AllAppsList {
    private static final String TAG = "AllAppsList";

    public static final int DEFAULT_APPLICATIONS_NUMBER = 42;

    /**
     * The list off all apps.
     */
    public ArrayList<AppInfo> data =
            new ArrayList<AppInfo>(DEFAULT_APPLICATIONS_NUMBER);
    /**
     * The list of apps that have been added since the last notify() call.
     */
    public ArrayList<AppInfo> added =
            new ArrayList<AppInfo>(DEFAULT_APPLICATIONS_NUMBER);
    /**
     * The list of apps that have been removed since the last notify() call.
     */
    public ArrayList<AppInfo> removed = new ArrayList<AppInfo>();
    /**
     * The list of apps that have been modified since the last notify() call.
     */
    public ArrayList<AppInfo> modified = new ArrayList<AppInfo>();

    private IconCache mIconCache;

    private AppFilter mAppFilter;

    ///add for OP01, OP02,
    public AllAppsListPluginEx mPluginEx;

    /**
     * Boring constructor.
     */
    public AllAppsList(IconCache iconCache, AppFilter appFilter) {
        mIconCache = iconCache;
        mAppFilter = appFilter;

        ///add for OP01, OP02,
        mPluginEx = new AllAppsListPluginEx(this);
    }

    /**
     * Add the supplied ApplicationInfo objects to the list, and enqueue it into the
     * list to broadcast when notify() is called.
     * <p/>
     * If the app is already in the list, doesn't add it.
     */
    public void add(AppInfo info, Context context) {
        if (map == null) {
            map = getMap(context);
        }
        info.setAppType(map);
        if (mAppFilter != null && !mAppFilter.shouldShowApp(info.componentName)) {
            return;
        }
        if (findActivity(data, info.componentName, info.user)) {
            LauncherLog.i(TAG, "Application " + info + " already exists in app list, app = "
                    + info);
            return;
        }
        data.add(info);
        added.add(info);
    }

    private static Map<String, List<String>> map;

    private Map<String, List<String>> getMap(Context context) {
        Map<String, List<String>> map = new TreeMap<>();
        List<String> list = new ArrayList<>();
        String type = "";
        try {
            XmlPullParser mXmlPullParser = context.getResources().getXml(R.xml.app_type);

            int mEventType = mXmlPullParser.getEventType();
            while (mEventType != XmlPullParser.END_DOCUMENT) {
                if (mEventType == XmlPullParser.START_DOCUMENT) {
                } else if (mEventType == XmlPullParser.END_DOCUMENT) {
                } else if (mEventType == XmlPullParser.START_TAG) {
                    String name = mXmlPullParser.getName();
                    if(name.equals("type")){
                        type = mXmlPullParser.getAttributeValue(null,"name");
                    }
                    if (name.equals("item")) {
                        String enable = mXmlPullParser.getAttributeValue(null,"calssName");
                        if (enable != null) {
                            list.add(enable);
                        }
                    }
                } else if (mEventType == XmlPullParser.END_TAG) {
                    String name = mXmlPullParser.getName();
                    if (name.equals("type")) {
                        map.put(type, list);
                        list = new ArrayList<>();
                    }
                } else if (mEventType == XmlPullParser.TEXT) {
                }
                mEventType = mXmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void clear() {
        if (LauncherLog.DEBUG) {
            LauncherLog.d(TAG, "clear all data in app list: app size = " + data.size());
        }

        data.clear();
        // TODO: do we clear these too?
        added.clear();
        removed.clear();
        modified.clear();
    }

    public int size() {
        return data.size();
    }

    public AppInfo get(int index) {
        return data.get(index);
    }

    /**
     * Add the icons for the supplied apk called packageName.
     */
    public void addPackage(Context context, String packageName, UserHandleCompat user) {
        final LauncherAppsCompat launcherApps = LauncherAppsCompat.getInstance(context);
        final List<LauncherActivityInfoCompat> matches = launcherApps.getActivityList(packageName,
                user);

        for (LauncherActivityInfoCompat info : matches) {
            add(new AppInfo(context, info, user, mIconCache), context);
        }
    }

    /**
     * Remove the apps for the given apk identified by packageName.
     */
    public void removePackage(String packageName, UserHandleCompat user) {
        final List<AppInfo> data = this.data;
        if (LauncherLog.DEBUG) {
            LauncherLog.d(TAG, "removePackage: packageName = " + packageName
                    + ", data size = " + data.size());
        }

        for (int i = data.size() - 1; i >= 0; i--) {
            AppInfo info = data.get(i);
            final ComponentName component = info.intent.getComponent();
            if (info.user.equals(user) && packageName.equals(component.getPackageName())) {
                removed.add(info);
                data.remove(i);
            }
        }
    }

    public void updateIconsAndLabels(HashSet<String> packages, UserHandleCompat user,
                                     ArrayList<AppInfo> outUpdates) {
        for (AppInfo info : data) {
            if (info.user.equals(user) && packages.contains(info.componentName.getPackageName())) {
                mIconCache.updateTitleAndIcon(info);
                outUpdates.add(info);
            }
        }
    }

    /**
     * Add and remove icons for this package which has been updated.
     */
    public void updatePackage(Context context, String packageName, UserHandleCompat user) {
        final LauncherAppsCompat launcherApps = LauncherAppsCompat.getInstance(context);
        final List<LauncherActivityInfoCompat> matches = launcherApps.getActivityList(packageName,
                user);
        if (LauncherLog.DEBUG) {
            LauncherLog.d(TAG, "updatePackage: packageName = " + packageName
                    + ", matches = " + matches.size());
        }

        if (matches.size() > 0) {
            // Find disabled/removed activities and remove them from data and add them
            // to the removed list.
            for (int i = data.size() - 1; i >= 0; i--) {
                final AppInfo applicationInfo = data.get(i);
                final ComponentName component = applicationInfo.intent.getComponent();
                if (user.equals(applicationInfo.user)
                        && packageName.equals(component.getPackageName())) {
                    if (!findActivity(matches, component)) {
                        removed.add(applicationInfo);
                        data.remove(i);
                    }
                }
            }

            // Find enabled activities and add them to the adapter
            // Also updates existing activities with new labels/icons
            for (final LauncherActivityInfoCompat info : matches) {
                AppInfo applicationInfo = findApplicationInfoLocked(
                        info.getComponentName().getPackageName(), user,
                        info.getComponentName().getClassName());
                if (applicationInfo == null) {
                    add(new AppInfo(context, info, user, mIconCache), context);
                } else {
                    mIconCache.getTitleAndIcon(applicationInfo, info, true /* useLowResIcon */);
                    modified.add(applicationInfo);
                }
            }
        } else {
            // Remove all data for this package.
            for (int i = data.size() - 1; i >= 0; i--) {
                final AppInfo applicationInfo = data.get(i);
                final ComponentName component = applicationInfo.intent.getComponent();
                if (user.equals(applicationInfo.user)
                        && packageName.equals(component.getPackageName())) {
                    if (LauncherLog.DEBUG) {
                        LauncherLog.d(TAG, "Remove application from launcher: component = "
                                + component);
                    }
                    removed.add(applicationInfo);
                    mIconCache.remove(component, user);
                    data.remove(i);
                }
            }
        }
    }


    /**
     * Returns whether <em>apps</em> contains <em>component</em>.
     */
    static boolean findActivity(List<LauncherActivityInfoCompat> apps,
                                ComponentName component) {
        for (LauncherActivityInfoCompat info : apps) {
            if (info.getComponentName().equals(component)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Query the launcher apps service for whether the supplied package has
     * MAIN/LAUNCHER activities in the supplied package.
     */
    static boolean packageHasActivities(Context context, String packageName,
                                        UserHandleCompat user) {
        final LauncherAppsCompat launcherApps = LauncherAppsCompat.getInstance(context);
        return launcherApps.getActivityList(packageName, user).size() > 0;
    }

    /**
     * Returns whether <em>apps</em> contains <em>component</em>.
     */
    private static boolean findActivity(ArrayList<AppInfo> apps, ComponentName component,
                                        UserHandleCompat user) {
        final int N = apps.size();
        for (int i = 0; i < N; i++) {
            final AppInfo info = apps.get(i);
            if (info.user.equals(user) && info.componentName.equals(component)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find an ApplicationInfo object for the given packageName and className.
     */
    private AppInfo findApplicationInfoLocked(String packageName, UserHandleCompat user,
                                              String className) {
        for (AppInfo info : data) {
            final ComponentName component = info.intent.getComponent();
            if (user.equals(info.user) && packageName.equals(component.getPackageName())
                    && className.equals(component.getClassName())) {
                return info;
            }
        }
        return null;
    }
}
