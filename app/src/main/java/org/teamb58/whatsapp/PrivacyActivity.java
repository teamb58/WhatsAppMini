package org.teamb58.whatsapp;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {

    RelativeLayout reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResID("activity_privacy", "layout"));

        reset = findViewById(getResID("reset", "id"));
        reset();

    }

    private void reset()
    {
        reset.setOnClickListener(new PrivClick(this));
    }

    private int getResID(String name, String type)
    {
        return getResources().getIdentifier(name, type, getPackageName());
    }
}
