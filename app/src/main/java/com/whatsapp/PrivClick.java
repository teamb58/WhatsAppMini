package com.whatsapp;

import android.content.Context;
import android.preference.Preference;
import android.widget.Toast;

import x.Constant;
import x.Main;

public class PrivClick implements Preference.OnPreferenceClickListener {
    private Context context;

    public PrivClick(Context applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        WhatsApp.sharedPreferences.edit().clear().apply();
        Toast.makeText(context, context.getResources().getIdentifier("resett","string",context.getPackageName()), Toast.LENGTH_SHORT).show();
        return true;
    }
}
