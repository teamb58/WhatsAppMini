package org.teamb58.whatsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static org.teamb58.whatsapp.MainActivity.link;


public class Update extends AsyncTask<String, String, String> {
    private final Context ctx;
    private String url = null;
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

    public void onPostExecute(String s) {
        AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder(this.ctx);
        if ((this.value > vercod(this.ctx))) {

            alertDialog$Builder
                    .setTitle(Main.getString(this.ctx, "nuf"))
                    .setMessage(Main.getString(this.ctx, "updatetext"))
                    .setPositiveButton(Main.getString(this.ctx, "dldnw"), new Download(this.ctx, this.url))
                    .setNegativeButton(Main.getString(this.ctx, "cancel"), new Cancel())
                    .setCancelable(false);
        }
        else if (s.equals("0")) {
            alertDialog$Builder
                    .setTitle(Main.getString(this.ctx, "utitleerr"))
                    .setMessage(Main.getString(this.ctx,"umessageerr"));

        }
        else return;
        //this.progDlg.dismiss();
        alertDialog$Builder
                .create()
                .show();
    }

    public void onPreExecute() {

    }

}