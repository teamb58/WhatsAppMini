package x;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Update extends AsyncTask<String, String, String> {
    private Context ctx;
    private String url = null;
    private String value = "0";

    public Update(Context ctx2) {
        this.ctx = ctx2;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... array) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
            StringBuilder string = new StringBuilder();
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                string.append(line);
            }
            final JSONObject jsonObject = new JSONObject(string.toString());
            this.value = jsonObject.getString("version");
            this.url = jsonObject.getString("url");
            return "1";
        }
        catch (Exception e) {
            return "0";
        }
    }

    private int vercod() {
        int vername;
        try {
            vername = this.ctx.getPackageManager().getPackageInfo(this.ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            vername = 0;
        }
        return vername;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String s) {
        if (Integer.parseInt(this.value) > vercod() && time()) {
            TextView textView = new TextView(this.ctx);
            textView.setText(Constant.updatetext);
            AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.ctx);
            alertDialog$Builder.setTitle(Constant.nuf);
            alertDialog$Builder.setView(textView);
            alertDialog$Builder.setPositiveButton(Constant.dldnw, new Download(this.ctx, this.url));
            alertDialog$Builder.setNegativeButton(Constant.later, new Cancel(1));
            alertDialog$Builder.create().show();
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
    }

    private boolean time() {
        return System.currentTimeMillis() - Main.sharedPreferences.getLong(Constant.remind, 0) > 21600000;
    }
}