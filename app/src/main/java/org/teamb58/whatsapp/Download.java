package org.teamb58.whatsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

class Download implements DialogInterface.OnClickListener {
    private final Context context;
    private final String url;

    Download(Context context2, String s) {
        this.context = context2;
        this.url = s;
    }

    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
    }

}
