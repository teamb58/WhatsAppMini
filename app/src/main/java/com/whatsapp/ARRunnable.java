package com.whatsapp;

import com.b58works.whatsapp.BuildConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.whatsapp.WhatsApp.jabber;
import static com.whatsapp.WhatsApp.revokestr;
import static com.whatsapp.WhatsApp.sharedPreferences;

class ARRunnable implements Runnable {
    public void run() {
        a();
    }

    static String strip(String str) {
        return (str.contains("g.us") || str.contains("s.whatsapp.net") || str.contains("broadcast")) ? str.substring(0, str.indexOf("@")) : str;
    }

    private static void a() {
        String str = strip(jabber);
        String str3 = strip(jabber);
        String str2 = revokestr;
        String str4;
        String[] b2 = b(str);
        if (b2 != null) {
            HashSet<String> hashSet = new HashSet<>();
            Collections.addAll(hashSet, b2);
            hashSet.addAll(Collections.singletonList(str2));
            str4 = Arrays.toString(hashSet.toArray());
        } else {
            str4 = "[" + str2 + "]";
        }
        if (str4 != null) {
            sharedPreferences.edit().putString(str3 + "_revoked", str4).apply();
        }
    }

    static String[] b(String str) {
        try {
            String str2 = sharedPreferences.getString(str + "_revoked", BuildConfig.FLAVOR);
            if (!str2.equals(BuildConfig.FLAVOR)) {
                return StringToStringArray(str2);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[] StringToStringArray(String str) {
        return str.substring(1, str.length() - 1).replaceAll("\\s", BuildConfig.FLAVOR).split(",");
    }
}
