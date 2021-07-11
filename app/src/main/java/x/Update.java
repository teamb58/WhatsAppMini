package x;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static com.b58works.whatsapp.MainActivity.link;


public class Update extends AsyncTask<String, String, String> {
    private final Context ctx;
    private String url = null;
    //private ProgressDialog progDlg;
    private int value = 0;


    public Update(Context ctx2) {
        this.ctx = ctx2;
    }

    public String doInBackground(String... array) {
        try {
            StringBuilder string = new StringBuilder();
            int check;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(Constant.txt).openStream()));
            string.append(bufferedReader.readLine());
            check = Integer.parseInt(new JSONObject(string.toString()).getString("who"));
            if (check == 1) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new URL(link).openStream()));
                string = new StringBuilder();
                string.append(br.readLine());
            }
            final JSONObject jsonObject = new JSONObject(string.toString());
            this.value = Integer.parseInt(jsonObject.getString("version"));
            this.url = jsonObject.getString("url");
            return "1";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    static int vercod(Context context) {
        int vername;
        try {
            vername = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            vername = 0;
        }
        return vername;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String s) {
        AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.ctx);
        if ((this.value > vercod(this.ctx))) {

            alertDialog$Builder
                    .setTitle(Constant.nuf)
                    .setMessage(Constant.updatetext)
                    .setPositiveButton(Constant.dldnw, new Download(this.ctx, this.url))
                    .setNegativeButton(Constant.cancel, new Cancel())
                    .setCancelable(false);
        }
        else if (s.equals("0")) {
            alertDialog$Builder.setTitle(Constant.utitleerr).setMessage(Constant.umessageerr);

        }
        else {
            /*alertDialog$Builder.setTitle(Constant.utitleg).setMessage((Constant.umessageg));
            alertDialog$Builder.setPositiveButton(Constant.ok, new Cancel());*/
            return;

        }
        //this.progDlg.dismiss();
        alertDialog$Builder
                .create()
                .show();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        /*(this.progDlg = new ProgressDialog(this.ctx)).setMessage("Please wait while we check for updates...");
        this.progDlg.setCancelable(true);
        this.progDlg.show();*/
    }

}