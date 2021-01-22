package x;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.webkit.WebView;
import android.widget.Toast;

import com.whatsapp.Broadcast;

class Download implements DialogInterface.OnClickListener {
    private final Context context;
    private final String url;
    //private final int check;

    Download(Context context2, String s/*, int i*/) {
        this.context = context2;
        this.url = s;
        //this.check = i;
    }

    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();

        download();
        context.getSharedPreferences(Constant.pref, 0).edit().putBoolean(Main.value("_iZmd"), true).apply();
    }

    private void download() {
        response();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.url));
        request
                .setDescription(Constant.desc)
                .setTitle(Constant.title)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalFilesDir(this.context, Environment.DIRECTORY_DOWNLOADS, Constant.file)
                .allowScanningByMediaScanner();
        long downloadId = ((DownloadManager) this.context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        Main.sharedPreferences.edit().putLong("downloadid", downloadId).apply();
        BroadcastReceiver downloadReceiver = new Broadcast();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(downloadReceiver, filter);
        Toast.makeText(context, Constant.updtst, Toast.LENGTH_SHORT).show();
    }

    static String getname() {
        return "B58";
    }

    private void response() {
        WebView view = new WebView(this.context.getApplicationContext());
        view.loadUrl(Constant.php + getname());
        AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.context);
        alertDialog$Builder
                .setTitle(Constant.nuf)
                .setView(view)
                .setNegativeButton(Constant.cancel, new Cancel(0))
                .create().show();
    }
}
