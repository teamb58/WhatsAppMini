package com.whatsapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class PrivClick implements View.OnClickListener {
    private final Context context;

    public PrivClick(Context applicationContext) {
        this.context = applicationContext;
    }


    @Override
    public void onClick(View v) {
        WhatsApp.sharedPreferences.edit().clear().apply();
        Toast.makeText(context, context.getResources().getIdentifier("resett","string",context.getPackageName()), Toast.LENGTH_SHORT).show();
    }
}
