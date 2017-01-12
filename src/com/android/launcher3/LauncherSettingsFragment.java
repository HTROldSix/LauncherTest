package com.android.launcher3;

/**
 * Created by qi.tao on 2017/1/5.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.launcher3.allapps.AllAppsGridAdapter;

import java.io.IOException;

/**
 * This fragment shows the launcher preferences.
 */
public class LauncherSettingsFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {
    SwitchPreference pref;
    SwitchPreference prefGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.launcher_preferences);

        pref = (SwitchPreference) findPreference(
                Utilities.ALLOW_ROTATION_PREFERENCE_KEY);
        pref.setPersistent(false);
        prefGroup = (SwitchPreference) findPreference(
                Utilities.INTELLIGENT_GROUPING_KEY);

        Bundle extras = new Bundle();
        extras.putBoolean(LauncherSettings.Settings.EXTRA_DEFAULT_VALUE, false);
        Bundle value = getActivity().getContentResolver().call(
                LauncherSettings.Settings.CONTENT_URI,
                LauncherSettings.Settings.METHOD_GET_BOOLEAN,
                Utilities.ALLOW_ROTATION_PREFERENCE_KEY, extras);
        pref.setChecked(value.getBoolean(LauncherSettings.Settings.EXTRA_VALUE));

        pref.setOnPreferenceChangeListener(this);
        prefGroup.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == pref) {
            Bundle extras = new Bundle();
            extras.putBoolean(LauncherSettings.Settings.EXTRA_VALUE, (Boolean) newValue);
            getActivity().getContentResolver().call(
                    LauncherSettings.Settings.CONTENT_URI,
                    LauncherSettings.Settings.METHOD_SET_BOOLEAN,
                    preference.getKey(), extras);
            return true;
        } else {
            prefGroup.setChecked((Boolean) newValue);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("allappmode", 4);
            SharedPreferences.Editor spe = sharedPreferences.edit();
            spe.putBoolean("appclassify", (Boolean) newValue);
            spe.commit();
        }

        return false;
    }
}
