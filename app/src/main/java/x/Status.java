package x;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.b58works.whatsapp.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import static x.Main.strip;

public class Status {

    public static int S = 0;
    public static HashMap T;

    public Status()
    {
        if (T == null) T = new HashMap();
    }

    public void downloadStatus(Activity activity, String jid)
    {
        /* Hardcoding the media status file and text status
        String for now.
        This shall be replaced with the relevant file during
        implementation.
         */
        File file = MainActivity.statusf;
        String status = MainActivity.status;

        //Copy text status to Clipboard.
        if (status != null) {
            ((ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE))
                    .setPrimaryClip(ClipData.newPlainText("textlabel", status));
            Toast.makeText(activity, Constant.text, Toast.LENGTH_SHORT).show();
        }
        // Save media file
        else if (file != null)
            saveMediaStatus(activity, jid, file);
    }

    public void y(Object o, String jid) {
        if (!(o instanceof List)) {
            return;
        }
        T.put(jid, o);
    }

    private void saveMediaStatus(Context context, String jid, File file) {
        try {
            String destFile = getDestFileName(context, jid, file);
            if (destFile == null) {
                throw new IOException();
            } else if (new File(destFile).exists()) {
                Toast.makeText(context, Constant.already, Toast.LENGTH_SHORT).show();
            } else {
                copyFile(file, new File(destFile));
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(destFile))));
                Toast.makeText(context, Constant.media + destFile, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Toast.makeText(context, Constant.error + e2.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void copyFile(File source, File dest)
    {
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private String getDestFileName(Context context, String jid, File statusFile) {
        try {
            File file = new File(context.getExternalFilesDir(null), Constant.dirname);
            if (!file.exists() && !file.mkdirs()) {
                return null;
            }
            return file.getPath() + File.separator + strip(jid) + Constant.filename + statusFile.getName();
        } catch (Exception unused) {
            return null;
        }
    }

}
