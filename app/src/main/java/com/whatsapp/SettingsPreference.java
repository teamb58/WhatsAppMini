package com.whatsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.preference.TwoStatePreference;

import com.b58works.whatsapp.R;

import x.Constant;
import x.Main;
import x.PrivClick;

public class SettingsPreference extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        getPreferenceManager().setSharedPreferencesName(Constant.pref);
        addPreferencesFromResource(R.xml.preferences_app);
        setTitle(Constant.preference);
        setPrivacy();setBackup();reset();
        sethideseen();bor();ar();hidefwd();hidestatus();
        cblue();cdouble();ctype();crecord();cplay();
        gblue();gdouble();gtype();grecord();gplay();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if ((p instanceof SwitchPreference)) {
            Main.sharedPreferences.edit().putBoolean(key, ((TwoStatePreference) p).isChecked()).apply();
        }
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

    public void setPrivacy()
    {
        Preference priv = findPreference("privacy");
        priv.setIcon(R.drawable.privacy);
        priv.setTitle(Constant.privacy);
        priv.setSummary(Constant.privacysum);
    }

    public void setBackup()
    {
        BackupPref backupPref = (BackupPref) findPreference("backup");
        backupPref.setIcon(R.drawable.backup);
        backupPref.setTitle(Constant.backup);
        backupPref.setSummary(Constant.backupsum);
    }

    private void sethideseen()
    {
        Preference preference = findPreference("hideseen");
        preference.setIcon(R.drawable.message_unsent);
        preference.setTitle(Constant.hideseen);
        preference.setSummary(Constant.hideseensum);
    }

    private void bor()
    {
        Preference preference = findPreference("bor");
        preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setTitle(Constant.bor);
        preference.setSummary(Constant.borsum);
    }

    private void ar()
    {
        Preference preference = findPreference("ar");
        preference.setIcon(R.drawable.message_got_receipt_revoked);
        preference.setTitle(Constant.ar);
        preference.setSummary(Constant.arsum);
    }

    private void hidefwd()
    {
        Preference preference = findPreference("hidefwd");
        preference.setIcon(R.drawable.ic_forward_message);
        preference.setTitle(Constant.hideforward);
        preference.setSummary(Constant.hideforwardsum);
    }

    private void hidestatus()
    {
        Preference preference = findPreference("hidestatus");
        preference.setIcon(R.drawable.design_ic_visibility);
        preference.setTitle(Constant.hidestatus);
        preference.setSummary(Constant.hidestatussum);
    }

    private void cblue()
    {
        Preference preference = findPreference("C_hideread");
        preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setTitle(Constant.hideblue);
        preference.setSummary(Constant.dt + Constant.contact + Constant.hbcsum);
    }

    private void cdouble()
    {
        Preference preference = findPreference("C_hidereceipt");
        preference.setIcon(R.drawable.message_got_receipt_from_target);
        preference.setTitle(Constant.hidedouble);
        preference.setSummary(Constant.dt + Constant.contact + Constant.hdcsum);
    }

    private void cplay()
    {
        Preference preference = findPreference("C_hideplay");
        preference.setIcon(R.drawable.mic_played);
        preference.setTitle(Constant.hideplay);
        preference.setSummary(Constant.dt + Constant.contact + Constant.hpcsum);
    }

    private void ctype()
    {
        Preference preference = findPreference("C_hidetype");
        preference.setIcon(R.drawable.type);
        preference.setTitle(Constant.hidetype);
        preference.setSummary(Constant.dt + Constant.contact + Constant.htcsum);
    }

    private void crecord()
    {
        Preference preference = findPreference("C_hiderecord");
        preference.setIcon(R.drawable.mic_new);
        preference.setTitle(Constant.hiderecord);
        preference.setSummary(Constant.dt + Constant.contact + Constant.hrcsum);
    }

    private void gblue()
    {
        Preference preference = findPreference("G_hideread");
        preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setTitle(Constant.hideblue);
        preference.setSummary(Constant.dt + Constant.group + Constant.hbcsum);
    }

    private void gdouble()
    {
        Preference preference = findPreference("G_hidereceipt");
        preference.setIcon(R.drawable.message_got_receipt_from_target);
        preference.setTitle(Constant.hidedouble);
        preference.setSummary(Constant.dt + Constant.group + Constant.hdcsum);
    }

    private void gplay()
    {
        Preference preference = findPreference("G_hideplay");
        preference.setIcon(R.drawable.mic_played);
        preference.setTitle(Constant.hideplay);
        preference.setSummary(Constant.dt + Constant.group + Constant.hpcsum);
    }

    private void gtype()
    {
        Preference preference = findPreference("G_hidetype");
        preference.setIcon(R.drawable.type);
        preference.setTitle(Constant.hidetype);
        preference.setSummary(Constant.dt + Constant.group + Constant.htcsum);
    }

    private void grecord()
    {
        Preference preference = findPreference("G_hiderecord");
        preference.setIcon(R.drawable.mic_new);
        preference.setTitle(Constant.hiderecord);
        preference.setSummary(Constant.dt + Constant.group + Constant.hrcsum);
    }

    private void reset()
    {
        Preference preference = findPreference("reset");
        preference.setTitle(Constant.resetp);
        preference.setSummary(Constant.resetps);
        preference.setOnPreferenceClickListener(new PrivClick(getApplicationContext()));
    }

}
