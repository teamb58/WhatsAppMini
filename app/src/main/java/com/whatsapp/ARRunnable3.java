package com.whatsapp;

import android.app.Activity;

class ARRunnable3 implements Runnable {

    private final Activity activity;

    ARRunnable3(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        activity.recreate();
    }
}
