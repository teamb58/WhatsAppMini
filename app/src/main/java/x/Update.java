package x;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Update extends AsyncTask<String, String, String> {
    private final Context ctx;
    private String url = null;
    private String value = "0";
    //private int check;


    public Update(Context ctx2) {
        this.ctx = ctx2;
    }

    public String doInBackground(String... array) {
        if (!Main.isAvailable())
        {
            try {
                StringBuilder string = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
                final String line = bufferedReader.readLine();

                string.append(line);
                final JSONObject jsonObject = new JSONObject(string.toString());
                this.value = jsonObject.getString("v");
                this.url = jsonObject.getString("url");
                return "1";
            }
            catch (Exception e) {
                e.printStackTrace();
                return "0";
            }
        }
        else
            return "0";
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
        if ((Integer.parseInt(this.value) > vercod()) || Main.isAvailable()) {
            AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.ctx);
            alertDialog$Builder
                    .setTitle(Constant.nuf)
                    .setMessage(Constant.updatetext)
                    .setPositiveButton(Constant.dldnw, new Download(this.ctx, this.url/*, check*/))
                    .setNegativeButton(Constant.later, new Cancel(this.ctx,1))
                    .setCancelable(false)
                    .create().show();
        }
        else
            Main.sharedPreferences.edit().putLong(Constant.remind, System.currentTimeMillis()).apply();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
    }

}