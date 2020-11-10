package x;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.b58works.whatsapp.MainActivity;
import com.whatsapp.SettingsPreference;

public class Main {

    public static SharedPreferences sharedPreferences;

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

    public static boolean HideCR(String jid, int n) {
        String s1 = "hidecr";
        Log.d(Constant.pref,s1);
        String s = strip(jid);
        if (n == 1) {
            if (getPrivacyB(s)) {
                return getPrivacyB(s + Constant.crecord);
            }
            return getPrivacyB(GetType(jid) + Constant.crecord);
        }
        else
        {
            if (getPrivacyB(s)) {
                return getPrivacyB(s + Constant.ccompose);
            }
            return getPrivacyB(GetType(jid) + Constant.ccompose);
        }
    }

    public static boolean HidePlay(String jab) {
        String s = "hideplay";
        Log.d(Constant.pref,s);
        String jid = strip(jab);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.cplay);
        }
        return getPrivacyB(GetType(jab) + Constant.cplay);
    }

    public static int HideForward(int i) {
        String s = "hidefwd";
        Log.d(Constant.pref,s);
        return (!getPrivacyB(Constant.hf) || i <= 0) ? i : i - 1;
    }

    public static boolean HideReceipt(String jab) {
        String s = "hidereceipt";
        Log.d(Constant.pref,s);
        String jid = strip(jab);
        if (getPrivacyB(jid)) {
            return getPrivacyB(jid + Constant.creceipt);
        }
        return getPrivacyB(GetType(jab) + Constant.creceipt);
    }

    public static boolean HideSeen() {
        String s = "hideseen";
        Log.d(Constant.pref,s);
        return getPrivacyB(Constant.hs);
    }

    public static boolean HideStatus(String jab) {
        String s = "hidestatus";
        Log.d(Constant.pref,s);
        String jid = strip(jab);
        if (!getPrivacyB(jid)) {
            return !getPrivacyB(jid + Constant.cstatus);
        }
        return !getPrivacyB(Constant.hst);
    }

    static Intent OpenChat(String str, Context homeActivity) {
        try {
            return new Intent(homeActivity, MainActivity.class).putExtra("jid", str).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } catch (Exception e) {
            return null;
        }
    }

    public static void addMenu(Activity homeActivity, MenuItem menuItem) {
        if (homeActivity instanceof MainActivity)
        {
            if (menuItem.getItemId() == getID(homeActivity, value("fh_lWYo"))) {
                homeActivity.startActivity(new Intent(homeActivity, SettingsPreference.class));
            }
            if (menuItem.getItemId() == getID(homeActivity, value("ZdZ")))
            {
                setdnd(!dnd());
                Intent intent = homeActivity.getIntent();
                homeActivity.finish();
                homeActivity.startActivity(intent);
            }
            if (menuItem.getItemId() == getID(homeActivity, value("Y^Wj"))) {
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
    }

    public static void Update(Activity activity)
    {
        String toast = Main.value("Fb[Wi[\u0016mW_j$\u0016J^[\u0016Wff\u0016_i\u0016][jj_d]\u0016ZemdbeWZ[Z");
        if(isNetworkAvailable(activity))
        {
            if(isdownloading())
                Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show();
            else if (time())
            {
                if (activity instanceof MainActivity)
                    new Update(activity).execute();
            }
        }
    }

    private static boolean isdownloading() {
        return Main.getPrivacyB(Main.value("_iZmd"));
    }

    static boolean isNetworkAvailable(Context ctx) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }
        catch (Exception ex) {
            return false;
        }
    }

    private static boolean time() {
        return System.currentTimeMillis() - Main.sharedPreferences.getLong(Constant.remind, 0) > 21600000;
    }

    public static void setMenu(Activity homeActivity, Menu menu)
    {
        if (homeActivity instanceof MainActivity)
        {
            menu.add(1, getID(homeActivity, value("fh_lWYo")), 0, Constant.mods);
            menu.add(1, getID(homeActivity, value("Y^Wj")), 0, Constant.newchat);
            MenuItem menuItem = menu.add(1,getID(homeActivity, value("ZdZ")),0,dndstr());
            menuItem.setIcon(dndimg(homeActivity));
            menuItem.setShowAsAction(2);
        }
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

    private static int dndimg(Context context)
    {
        if (dnd())
            return getDrawable(context, value("i_]dWbUed"));
        else
            return getDrawable(context, value("i_]dWbUe\\\\"));
    }

    public static void select(TextView textView)
    {
        String s = "Text select";
        textView.setTextIsSelectable(true);

    }

    private static int getID(Context context, String name)
    {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }

    private static int getDrawable(Context context, String name)
    {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
