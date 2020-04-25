package com.whatsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;

import x.Constant;
import x.Main;
import x.Update;

public class Broadcast extends BroadcastReceiver {

    File file = new File(Environment.getExternalStorageDirectory() + Constant.folder + Constant.file);
    public void onReceive(Context ctxt, Intent intent) {
        if (did() == intent.getLongExtra("extra_download_id", -1)) {
            installfile(ctxt);
            ctxt.getSharedPreferences(Constant.pref, 0).edit().putBoolean(Update.isdownload, false).apply();

        }
    }

    public void installfile(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".provider", this.file);
            intent.setDataAndType(uri, Constant.type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setDataAndType(Uri.fromFile(this.file), Constant.type);
        }
        context.startActivity(intent);
    }

    public static void delete() {
        File file = new File(Environment.getExternalStorageDirectory() + Constant.folder + Constant.file);
        if (file.exists())
        file.delete();
    }

    private long did() {
        return Main.sharedPreferences.getLong("downloadid", 0);
    }
}