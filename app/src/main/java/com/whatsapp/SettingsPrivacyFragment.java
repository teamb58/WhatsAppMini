package com.whatsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.TwoStatePreference;

public class SettingsPrivacyFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final Context ctx;

    public SettingsPrivacyFragment(SettingsActivity ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(getResID(), rootKey);
        reset();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if ((p instanceof SwitchPreferenceCompat))
            WhatsApp.sharedPreferences.edit().putBoolean(key, ((TwoStatePreference) p).isChecked()).apply();
        onStart();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    private void reset()
    {
        Preference preference = findPreference("reset");
        if (preference != null) {
            preference.setOnPreferenceClickListener(new PrivClick(getContext()));
        }
    }

    private int getResID()
    {
        return getResources().getIdentifier("preference_privacy", "xml",ctx.getPackageName());
    }
}
