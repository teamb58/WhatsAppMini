package org.teamb58.whatsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

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

}
