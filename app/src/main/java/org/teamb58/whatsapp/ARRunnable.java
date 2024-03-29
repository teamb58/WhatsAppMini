package org.teamb58.whatsapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

class ARRunnable implements Runnable {

    private final String jabber;
    public ARRunnable(String s) {
        jabber = s;
    }

    public void run() {
        a();
    }

    static String strip(String str) {
        return (str.contains("g.us") || str.contains("s.whatsapp.net") || str.contains("broadcast")) ? str.substring(0, str.indexOf("@")) : str;
    }

    private void a() {
        String str = strip(jabber);
        String str3 = strip(jabber);
        String str2 = WhatsApp.revokestr;
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
            WhatsApp.sharedPreferences.edit().putString(str3 + "_revoked", str4).apply();
        }
    }

    static String[] b(String str) {
        try {
            String str2 = WhatsApp.sharedPreferences.getString(str + "_revoked", "");
            if (!str2.equals("")) {
                return StringToStringArray(str2);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String[] StringToStringArray(String str) {
        return str.substring(1, str.length() - 1).replaceAll("\\s", "").split(",");
    }
}
