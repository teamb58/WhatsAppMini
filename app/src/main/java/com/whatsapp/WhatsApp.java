package com.whatsapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class WhatsApp {

    private static HashSet<String> b = null;
    private static Activity activity;
    public static String contains;
    public static SharedPreferences sharedPreferences;

    static String revokestr;


    public static boolean HideRead(Object o) {
        String jid = (String) o;
        if (getPrivacyB(ARRunnable.strip(jid))) {
            return getPrivacyB(ARRunnable.strip(jid) + "_HideRead");
        }
        return getPrivacyB(GetType(jid) + "_HideRead");
    }

    static boolean getPrivacyB(String str) {
        return sharedPreferences.getBoolean(str, false);
    }

    private static String GetType(String str) {
        if (str.contains("g.us")) {
            return "G";
        }
        if (str.contains("broadcast")) {
            return "B";
        }
        return str.contains("s.whatsapp.net") ? "C" : "ST";
    }

    public static boolean AntiRevoke(String s) {
        boolean b2;
        HashSet<String> hashSet;
        String jid = ARRunnable.strip(s);
        if (getPrivacyB(jid)) {
            b2 = getPrivacyB(jid + "_AR");
        } else {
            b2 = getPrivacyB("Antirevoke");
        }
        if (b2) {
            try {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(new ARRunnable(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
            final Activity activity1 = WhatsApp.activity;
            if (activity1 != null) {
                if (activity1.hasWindowFocus()) {
                    String[] ba = ARRunnable.b(ARRunnable.strip(s));
                    if (!(ba == null || (hashSet = b) == null)) {
                        Collections.addAll(hashSet, ba);
                    }

                    //activity1.startActivity(activity1.getIntent());
                    //activity1.overridePendingTransition(0, 0);
                    //activity1.getWindow().getDecorView().findViewById(android.R.id.content).postInvalidate();
                } /*else {
                    activity.runOnUiThread(new ARRunnable3(activity1));
                }*/
            }
        }
        return b2;
    }

    public static void isMrevoked(final TextView textView, final Context context, String s) {
        boolean bo = false;
        String value = sharedPreferences.getString(ARRunnable.strip(s) + "_revoked", "");

        String[] arr = null; HashSet<String> hashSet;
        if (!value.equals("")) arr = ARRunnable.StringToStringArray(value);

        if(arr == null)
            hashSet = null;
        else
            hashSet = new HashSet<>(Arrays.asList(arr));
        
        if (hashSet != null) {
            bo = hashSet.contains(contains);
        }
        if (bo) {
            textView.post(new ARRunnable2(context, textView));
        }
    }

    public static void onStart(Activity conversation, String s) {
        HashSet<String> hashSet;
        b = new HashSet<>();
        activity = conversation;
        String[] b2 = ARRunnable.b(ARRunnable.strip(s));
        if (b2 != null && (hashSet = b) != null) {
            Collections.addAll(hashSet, b2);
        }
    }


}
