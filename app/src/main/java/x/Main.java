package x;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.b58works.whatsapp.MainActivity;
import com.b58works.whatsapp.R;
import com.whatsapp.SettingsPreference;

public class Main {

    public Context context;
    private static String jabber;
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

    static boolean getPrivacyB(String str) {
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
        Log.d(Constant.pref,s1);
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
        Log.d(Constant.pref,s);
        String jid = strip(jabber);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.cplay);
        }
        return getPrivacyB(GetType(jabber) + Constant.hp);
    }

    public static int HideForward(int i) {
        String s = "hidefwd";
        Log.d(Constant.pref,s);
        return (!getPrivacyB(Constant.hf) || i <= 0) ? i : i - 1;
    }

    public static boolean HideReceipt() {
        String s = "hidereceipt";
        Log.d(Constant.pref,s);
        String jid = strip(jabber);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.creceipt);
        }
        return getPrivacyB(GetType(jabber) + Constant.hrc);
    }

    public static boolean HideSeen() {
        String s = "hideseen";
        Log.d(Constant.pref,s);
        return getPrivacyB(Constant.hs);
    }

    public static boolean HideStatus() {
        String s = "hidestatus";
        Log.d(Constant.pref,s);
        String jid = strip(jabber);
        if (!getPrivacyB(jid)) {
            return !getPrivacyB(Constant.hst);
        }
        return !getPrivacyB(jid + Constant.cstatus);
    }

    static Intent OpenChat(String str, Context homeActivity) {
        try {
            return new Intent(homeActivity, MainActivity.class).putExtra("jid", str).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } catch (Exception e) {
            return null;
        }
    }

    public static void addMenu(Context homeActivity, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.privacy) {
            homeActivity.startActivity(new Intent(homeActivity, SettingsPreference.class));
        }
        if (menuItem.getItemId() == R.id.dnd)
        {
            setdnd(!dnd());
            ((AlarmManager) homeActivity.getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC, 100L + System.currentTimeMillis(), PendingIntent.getActivity(homeActivity, 123456, homeActivity.getPackageManager().getLaunchIntentForPackage(homeActivity.getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT));
            System.exit(0);
        }
        if (menuItem.getItemId() == R.id.chat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
            builder.setTitle(Constant.newchat);
            builder.setMessage(Constant.enternum);
            EditText editText = new EditText(homeActivity);
            builder.setView(editText);
            builder.setPositiveButton(Constant.message,  new NewChat(editText, homeActivity));
            builder.setNegativeButton(Constant.cancel,  new Cancel(0));
            builder.show();
        }
    }

    public static void setMenuC(Menu menu) {
        menu.add(1, R.id.chat, 0, Constant.newchat);
    }

    public static void setMenuP(Menu menu) {
        menu.add(1, R.id.privacy, 0, Constant.mods);
    }

    public static int hashvalue() {
        return -1582839300 - sub();
    }

    private static int sub() {
        return 38;
    }

    public static void Show(final ViewGroup viewGroup, Activity activity)
    {
        if (activity instanceof MainActivity)
            viewGroup.postDelayed(new DNDcheck(viewGroup),7);
    }

    public static boolean dnd()
    {
        return sharedPreferences.getBoolean(Constant.dnd,false);
    }

    private static void setdnd(boolean b)
    {
        sharedPreferences.edit().putBoolean(Constant.dnd,b).apply();
    }

    private static String dndstr()
    {
        if (dnd())
            return Constant.enint;
        else
            return Constant.disint;
    }

    private static int dndimg()
    {
        if (dnd())
            return R.drawable.signal_on;
        else
            return R.drawable.signal_off;
    }

    public static void setMenuD(Menu menu)
    {
        MenuItem menuItem = menu.add(1,R.id.dnd,0,dndstr());
        menuItem.setIcon(dndimg());
        menuItem.setShowAsAction(2);
    }

    public static void select(TextView textView)
    {
        String s = "Text select";
        textView.setTextIsSelectable(true);
    }

}
