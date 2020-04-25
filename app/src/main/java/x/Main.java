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


}
