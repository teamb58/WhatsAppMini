package com.whatsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.preference.TwoStatePreference;

public class SettingsPreference extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        getPreferenceManager().setSharedPreferencesName("B58");
        addPreferencesFromResource(getResID("preferences_app", "xml"));
        setTitle(getResID("setting", "string"));
        setPrivacy();setBackup();reset();setMedia();
        sethideseen();bor();ar();hidefwd();hidestatus();
        cblue();cdouble();ctype();crecord();cplay();
        gblue();gdouble();gtype();grecord();gplay();
        stimq();stimsize();stvidd();imgq();imgres();imgsize();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if ((p instanceof SwitchPreference))
            WhatsApp.sharedPreferences.edit().putBoolean(key, ((TwoStatePreference) p).isChecked()).apply();
        else if ((p instanceof SeekBarPreference))
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt(key,((SeekBarPreference) p).getmCurrentValue()).apply();
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

    public void setPrivacy()
    {
        Preference priv = findPreference("privacy");
        //priv.setIcon(R.drawable.privacy);
        priv.setIcon(getResID("privacy", "drawable"));
        priv.setTitle(getResID("priv", "string"));
        priv.setSummary(getResID("privsum", "string"));
    }

    public void setMedia()
    {
        Preference media = findPreference("media");
        //media.setIcon(R.drawable.media);
        media.setIcon(getResID("media", "drawable"));
        media.setTitle(getResID("media", "string"));
        media.setSummary(getResID("mediasum", "string"));
    }

    public void setBackup()
    {
        Preference backupPref =  findPreference("backup");
        //backupPref.setIcon(R.drawable.backup);
        backupPref.setIcon(getResID("backup", "drawable"));
        backupPref.setTitle(getResID("backup", "string"));
        backupPref.setSummary(getResID("backupsum", "string"));
        backupPref.setOnPreferenceClickListener(new BackupPref(this));
    }

    private void sethideseen()
    {
        Preference preference = findPreference("hideseen");
        //preference.setIcon(R.drawable.message_unsent);
        preference.setIcon(getResID("message_unsent", "drawable"));
        preference.setTitle(getResID("hseen", "string"));
        preference.setSummary(getResID("hseensum", "string"));
    }

    private void bor()
    {
        Preference preference = findPreference("bor");
        //preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setIcon(getResID("message_got_read_receipt_from_target", "drawable"));
        preference.setTitle(getResID("bor", "string"));
        preference.setSummary(getResID("borsum", "string"));
    }

    private void ar()
    {
        Preference preference = findPreference("Antirevoke");
        //preference.setIcon(R.drawable.message_got_receipt_revoked);
        preference.setIcon(getResID("message_got_receipt_revoked", "drawable"));
        preference.setTitle(getResID("ar", "string"));
        preference.setSummary(getResID("arsum", "string"));
    }

    private void hidefwd()
    {
        Preference preference = findPreference("hidefwd");
        //preference.setIcon(R.drawable.ic_forward_message);
        preference.setIcon(getResID("ic_forward_message", "drawable"));
        preference.setTitle(getResID("hfwd", "string"));
        preference.setSummary(getResID("hfwdsum", "string"));
    }

    private void hidestatus()
    {
        Preference preference = findPreference("hidestatus");
        //preference.setIcon(R.drawable.design_ic_visibility);
        preference.setIcon(getResID("design_ic_visibility", "drawable"));
        preference.setTitle(getResID("hstatus", "string"));
        preference.setSummary(getResID("hstatussum", "string"));
    }

    private void cblue()
    {
        Preference preference = findPreference("C_HideRead");
        //preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setIcon(getResID("message_got_read_receipt_from_target", "drawable"));
        preference.setTitle(getResID("hread", "string"));
        preference.setSummary(getResID("hreadsum", "string"));
    }

    private void cdouble()
    {
        Preference preference = findPreference("C_HideReceipt");
        //preference.setIcon(R.drawable.message_got_receipt_from_target);
        preference.setIcon(getResID("message_got_receipt_from_target", "drawable"));
        preference.setTitle(getResID("hreceipt", "string"));
        preference.setSummary(getResID("hreceiptsum", "string"));
    }

    private void cplay()
    {
        Preference preference = findPreference("C_HidePlay");
        //preference.setIcon(R.drawable.mic_played);
        preference.setIcon(getResID("mic_played", "drawable"));
        preference.setTitle(getResID("hplay", "string"));
        preference.setSummary(getResID("hplaysum", "string"));
    }

    private void ctype()
    {
        Preference preference = findPreference("C_HideCompose");
        //preference.setIcon(R.drawable.type);
        preference.setIcon(getResID("type", "drawable"));
        preference.setTitle(getResID("htype", "string"));
        preference.setSummary(getResID("htypesum", "string"));
    }

    private void crecord()
    {
        Preference preference = findPreference("C_HideRecord");
        //preference.setIcon(R.drawable.mic_new);
        preference.setIcon(getResID("mic_new", "drawable"));
        preference.setTitle(getResID("hrec", "string"));
        preference.setSummary(getResID("hrecsum", "string"));
    }

    private void gblue()
    {
        Preference preference = findPreference("G_HideRead");
        //preference.setIcon(R.drawable.message_got_read_receipt_from_target);
        preference.setIcon(getResID("message_got_read_receipt_from_target", "drawable"));
        preference.setTitle(getResID("hread", "string"));
        preference.setSummary(getResID("hreadsum", "string"));
    }

    private void gdouble()
    {
        Preference preference = findPreference("G_HideReceipt");
        //preference.setIcon(R.drawable.message_got_receipt_from_target);
        preference.setIcon(getResID("message_got_receipt_from_target", "drawable"));
        preference.setTitle(getResID("hreceipt", "string"));
        preference.setSummary(getResID("hreceiptsum", "string"));
    }

    private void gplay()
    {
        Preference preference = findPreference("G_HidePlay");
        //preference.setIcon(R.drawable.mic_played);
        preference.setIcon(getResID("mic_played", "drawable"));
        preference.setTitle(getResID("hplay", "string"));
        preference.setSummary(getResID("hplaysum", "string"));
    }

    private void gtype()
    {
        Preference preference = findPreference("G_HideCompose");
        //preference.setIcon(R.drawable.type);
        preference.setIcon(getResID("type", "drawable"));
        preference.setTitle(getResID("htype", "string"));
        preference.setSummary(getResID("htypesum", "string"));
    }

    private void grecord()
    {
        Preference preference = findPreference("G_HideRecord");
        //preference.setIcon(R.drawable.mic_new);
        preference.setIcon(getResID("mic_new", "drawable"));
        preference.setTitle(getResID("hrec", "string"));
        preference.setSummary(getResID("hrecsum", "string"));
    }

    private void reset()
    {
        Preference preference = findPreference("reset");
        preference.setTitle(getResID("resetp", "string"));
        preference.setSummary(getResID("resetpsum", "string"));
        preference.setOnPreferenceClickListener(new PrivClick(getApplicationContext()));
    }

    private void stimq()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("status_image_quality");
        //seekBarPreference.setmMaxValue(100);
        seekBarPreference.setTitle(getResID("stimq", "string"));
        seekBarPreference.setSummary(getResID("stimqsum", "string"));
        seekBarPreference.setDefaultValue(50);
    }

    private void stimsize()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("status_image_max_edge");
        //seekBarPreference.setmMaxValue(10240);
        seekBarPreference.setTitle(getResID("stims", "string"));
        seekBarPreference.setSummary(getResID("stimssum", "string"));
        seekBarPreference.setDefaultValue(1024);
    }

    private void stvidd()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("status_video_max_duration");
        //seekBarPreference.setmMaxValue(900);
        seekBarPreference.setTitle(getResID("stvid", "string"));
        seekBarPreference.setSummary(getResID("stvidsum", "string"));
        seekBarPreference.setDefaultValue(45);
    }

    private void imgsize()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("image_max_kbytes");
        //seekBarPreference.setmMaxValue(10240);
        seekBarPreference.setTitle(getResID("imsize", "string"));
        seekBarPreference.setSummary(getResID("imsizesum", "string"));
        seekBarPreference.setDefaultValue(1024);
    }

    private void imgq()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("image_quality");
        //seekBarPreference.setmMaxValue(100);
        seekBarPreference.setTitle(getResID("imgqty", "string"));
        seekBarPreference.setSummary(getResID("imgqtysum", "string"));
        seekBarPreference.setDefaultValue(80);
    }

    private void imgres()
    {
        SeekBarPreference seekBarPreference = (SeekBarPreference)findPreference("image_max_edge");
        //seekBarPreference.setmMaxValue(3840);
        seekBarPreference.setTitle(getResID("imgres", "string"));
        seekBarPreference.setSummary(getResID("imgressum", "string"));
        seekBarPreference.setDefaultValue(1280);
    }

    private int getResID(String name, String type)
    {
        return getResources().getIdentifier(name,type,getPackageName());
    }

}
