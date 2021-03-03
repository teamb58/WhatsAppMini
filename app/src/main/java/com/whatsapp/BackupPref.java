package com.whatsapp;

import android.content.Context;
import android.os.Environment;
import android.preference.Preference;

import java.io.File;

public class BackupPref extends Preference implements Preference.OnPreferenceClickListener {

    private final Context context;
    BackupPref(Context context) {
        super(context);
        this.context = context;
    }

    public boolean onPreferenceClick(Preference preference) {
        new CopyingTask(this.context, new File(Environment.getDataDirectory(), "data/com.whatsapp"), new File(Environment.getExternalStorageDirectory(), "WhatsApp/Backup")).execute();
        return true;
    }
}
