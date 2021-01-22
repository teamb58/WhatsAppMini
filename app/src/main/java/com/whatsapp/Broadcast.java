package com.whatsapp;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;

import x.Constant;

public class Broadcast extends BroadcastReceiver {

    String type = "application/vnd.android.package-archive";

    public void onReceive(Context ctxt, Intent intent) {
        long id = intent.getLongExtra("extra_download_id", -1);
        if (did() == id) {
            checkDownloadStatus(id, ctxt);
        }

        Log.d(Constant.pref, Constant.pref);
    }

    public void installfile(Context context) {
        WhatsApp.sharedPreferences.edit().putBoolean(Constant.updated, true).apply();
        WhatsApp.sharedPreferences.edit().putLong(Constant.recent,System.currentTimeMillis()).apply();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        File file = new File(context.getExternalFilesDir(null) + File.separator + Environment.DIRECTORY_DOWNLOADS, Constant.file);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".provider", file);
            intent.setDataAndType(uri, type);
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private long did() {
        return WhatsApp.sharedPreferences.getLong("downloadid", 0);
    }

    private void checkDownloadStatus(long downloadReference, Context context) {
        DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
        myDownloadQuery.setFilterById(downloadReference);
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(myDownloadQuery);
        if (cursor.moveToFirst()) {

            int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columnIndex);

            if (status == DownloadManager.STATUS_FAILED)
                WhatsApp.sharedPreferences.edit().putBoolean(Constant.dwnf, true).apply();
            else if (status == DownloadManager.STATUS_SUCCESSFUL)
                installfile(context);
            WhatsApp.sharedPreferences.edit().putBoolean(Constant.isdwn, false).apply();
            context.unregisterReceiver(this);
        }
    }
}