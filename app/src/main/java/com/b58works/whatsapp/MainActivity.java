package com.b58works.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.whatsapp.Privacy;
import com.whatsapp.WhatsApp;

import x.Main;

public class MainActivity extends AppCompatActivity {

    public static final String link = "https://teamb58.org/w.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("B58", 0);
        Main.sharedPreferences = WhatsApp.sharedPreferences = preferences;

        Main.updated(this);
        logger();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        Main.setMenu(this, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Main.addMenu(this, item);
        return super.onOptionsItemSelected(item);
    }

    private void logger()
    {
        String s = Main.HideCR("B58",0) + String.valueOf(Main.HideForward(0)) + Main.HidePlay("b58") + Main.HideReceipt("b58")
                + Main.HideSeen() + Main.HideStatus("b58") + Main.hashvalue() + WhatsApp.AntiRevoke("b58") + WhatsApp.HideRead(null);

        Main.select(null);
        Privacy.setJabber(null);
        Main.Show(null, this);
        WhatsApp.isMrevoked(null, this, "b58");
        WhatsApp.onStart(this, "b58");

        Log.d("B58", s);
    }
}
