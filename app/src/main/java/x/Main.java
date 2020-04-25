package x;

import android.content.Context;
import android.content.SharedPreferences;

public class Main {

    private Context context;
    public static String jabber;
    public static SharedPreferences sharedPreferences;

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
        sharedPreferences = this.context.getSharedPreferences(Constant.pref,0);
    }

    static String value(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c[i] + 10);
        }
        return String.valueOf(c);
    }

    private static boolean getPrivacyB(String str) {
        return sharedPreferences.getBoolean(str, false);
    }

    private static String strip(String str) {
        return (str.contains(Constant.gid) || str.contains(Constant.jid) || str.contains(Constant.broadcast)) ? str.substring(0, str.indexOf("@")) : str;
    }

    private static String GetType(String str) {
        if (str.contains(Constant.gid)) {
            return "G";
        }
        if (str.contains(Constant.broadcast)) {
            return "B";
        }
        return str.contains(Constant.jid) ? "C" : "ST";
    }

    public static boolean HideCR(int n) {
        String s1 = "hidecr";
        String s = strip(jabber);
        if (n == 1) {
            if (getPrivacyB(s)) {
                return getPrivacyB(s + Constant.crecord);
            }
            return getPrivacyB(GetType(jabber) + Constant.hrec);
        }
        else
        {
            if (getPrivacyB(s)) {
                return getPrivacyB(s + Constant.ccompose);
            }
            return getPrivacyB(GetType(jabber) + Constant.hc);
        }
    }

    public static boolean HidePlay() {
        String s = "hideplay";
        String jid = strip(jabber);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.cplay);
        }
        return getPrivacyB(GetType(jabber) + Constant.hp);
    }

    public static int HideForward(int i) {
        String s = "hidefwd";
        return (!getPrivacyB(Constant.hf) || i <= 0) ? i : i - 1;
    }

    public static boolean HideReceipt() {
        String s = "hidereceipt";
        String jid = strip(jabber);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.creceipt);
        }
        return getPrivacyB(GetType(jabber) + Constant.hrc);
    }

    public static boolean HideSeen() {
        String s = "hideseen";
        return getPrivacyB(Constant.hs);
    }

    public static boolean HideStatus() {
        String s = "hidestatus";
        String jid = strip(jabber);
        if (!getPrivacyB(jid)) {
            return !getPrivacyB(Constant.hst);
        }
        return !getPrivacyB(jid + Constant.cstatus);
    }

}
