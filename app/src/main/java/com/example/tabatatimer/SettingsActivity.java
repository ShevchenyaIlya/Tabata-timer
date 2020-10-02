package com.example.tabatatimer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        public static final String PREF_STYLE = "app_theme";
        public static final String PREF_LANGUAGE = "language";
        private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference button = findPreference(getString(R.string.drop_text));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    databaseHelper.dropTable();
                    return true;
                }
            });

            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    if (s.equals(PREF_STYLE))
                    {
                        SwitchPreferenceCompat theme_preference = (SwitchPreferenceCompat) findPreference(s);
                        if (theme_preference.isChecked()) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                        else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
                    }

                    if (s.equals("language")) {
                        ListPreference listPreference = (ListPreference) findPreference(s);
                        String language = listPreference.getValue();
                        Locale locale = new Locale(language);
                        Locale.setDefault(locale);
                        Configuration config = getResources().getConfiguration();
                        config.locale = locale;
                        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                        FragmentTransaction tr = getFragmentManager().beginTransaction();
                        tr.replace(R.id.settings, new SettingsFragment());
                        tr.commit();
                    }

                    if (s.equals("font")) {
                        ListPreference listPreference = (ListPreference) findPreference(s);
                        float value = Float.parseFloat(listPreference.getValue());
                    }
                }
            };
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        }
    }


}