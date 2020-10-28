package com.whatsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;

public class Broadcast extends BroadcastReceiver {

    String type = "application/vnd.android.package-archive";

    public void onReceive(Context ctxt, Intent intent) {
        if (did() == intent.getLongExtra("extra_download_id", -1)) {
            installfile(ctxt);
            ctxt.getSharedPreferences("B58", 0).edit().putBoolean("isdwn", false).apply();

        }
    }

    public void installfile(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        File file = new File(context.getExternalFilesDir(null) + "/../../../../" + Environment.DIRECTORY_DOWNLOADS, "/Sn1.apk");
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".provider", file);
            intent.setDataAndType(uri, type);
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void updated(Context context) {
        File file = new File(context.getExternalFilesDir(null) + "/../../../../" + Environment.DIRECTORY_DOWNLOADS, "/Sn1.apk");
        int vercode = WhatsApp.sharedPreferences.getInt("vercode",0);
        int vercodeofapp = 0; boolean b = false;
        try {
            vercodeofapp = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        if(vercodeofapp > vercode){
            if (file.exists())
                b = file.delete();
            WhatsApp.sharedPreferences.edit().putInt("vercode", vercodeofapp).apply();
            if (b)
                Toast.makeText(context, context.getString(context.getResources().getIdentifier("upds","string",context.getPackageName())), Toast.LENGTH_SHORT).show();
        }
    }

    private long did() {
        return WhatsApp.sharedPreferences.getLong("downloadid", 0);
    }
}