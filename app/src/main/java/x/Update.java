package x;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

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
        try {
            StringBuilder string = new StringBuilder();
            final String line;
            new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
            BufferedReader bufferedReader;
            /*BufferedReader br = new BufferedReader(new InputStreamReader(new URL(Constant.samtxt).openStream()));
            check = Integer.parseInt(new JSONObject(bufferedReader.readLine()).getString("who"));
            if(check == 1)
                line = br.readLine();
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
                line = bufferedReader.readLine();
            }*/

            bufferedReader = new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
            line = bufferedReader.readLine();

            string.append(line);
            final JSONObject jsonObject = new JSONObject(string.toString());
            this.value = jsonObject.getString("version1");
            this.url = jsonObject.getString("url");
            return "1";
        }
        catch (Exception e) {
            e.printStackTrace();
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
        if (Integer.parseInt(this.value) > vercod()) {
            TextView textView = new TextView(this.ctx);
            textView.setText(Constant.updatetext);
            AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.ctx);
            alertDialog$Builder.setTitle(Constant.nuf);
            alertDialog$Builder.setView(textView);
            alertDialog$Builder.setPositiveButton(Constant.dldnw, new Download(this.ctx, this.url/*, check*/));
            alertDialog$Builder.setNegativeButton(Constant.later, new Cancel(this.ctx,1));
            alertDialog$Builder.setCancelable(false);
            alertDialog$Builder.create().show();
        }
        else
            Main.sharedPreferences.edit().putLong(Constant.remind, System.currentTimeMillis()).apply();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
    }

}