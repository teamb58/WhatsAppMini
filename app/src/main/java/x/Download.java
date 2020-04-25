package x;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;

class Download implements DialogInterface.OnClickListener {
    private final Context context;
    private final String url;

    Download(Context context2, String s) {
        this.context = context2;
        this.url = s;
    }

    public void onClick(DialogInterface dialog, int which) {
        download();
        dialog.cancel();
    }

    private void download() {
        response();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.url));
        request.setDescription(Constant.desc);
        request.setTitle(Constant.title);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(2);
        request.setDestinationInExternalPublicDir(Constant.folder, Constant.file);
        long downloadId = ((DownloadManager) this.context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        Main.sharedPreferences.edit().putLong("downloadid", downloadId).apply();
        Log.d(Constant.pref, String.valueOf(downloadId));
    }

    private String getname() {
        return "B58";
    }

    private void response() {
        WebView view = new WebView(this.context);
        view.loadUrl(Constant.php + getname());
        AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.context);
        alertDialog$Builder.setTitle(Constant.nuf);
        alertDialog$Builder.setView(view);
        alertDialog$Builder.setNegativeButton(Constant.cancel, new Cancel(0));
        alertDialog$Builder.create().show();
    }
}
