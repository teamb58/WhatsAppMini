package org.teamb58.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String link = "https://teamb58.org/w.txt";

    public static String status;
    public static File statusf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("B58", 0);
        Main.sharedPreferences = WhatsApp.sharedPreferences = preferences;

        Main.updated(this);
        Main.updateCheck(this);
        logger();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        Main.setMenu(this, menu);
        Main.setStatusMenu(menu, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Main.addMenu(this, item);
        Main.onStatusClick(item.getItemId(), this, null);
        return super.onOptionsItemSelected(item);
    }

    private void logger()
    {
        String s = Main.HideCR("B58",0) + String.valueOf(Main.HideForward(0)) + Main.HidePlay("b58") + Main.HideReceipt("b58") + Main.ViewOnce("b58")
                + Main.HideSeen() + Main.HideStatus("b58") + Main.hashvalue() + WhatsApp.AntiRevoke("b58") + WhatsApp.HideRead(null);

        Main.select(null);
        Privacy.setJabber(null);
        Main.Show(null, this);
        WhatsApp.isMrevoked(null, this, "b58", true);
        WhatsApp.onStart(this, "b58");

        Status status = new Status();
        status.y(null, s);
        status.downloadStatus(this, s);
        Status.S = -1;

        Log.d("B58", s);
    }
}
