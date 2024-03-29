package org.teamb58.whatsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class Main {

    public static SharedPreferences sharedPreferences;

    static boolean getPrivacyB(String str) {
        return sharedPreferences.getBoolean(str, false);
    }

    static String strip(String str) {
        return (str.contains(Constant.gid) || str.contains(Constant.jid) || str.contains(Constant.broadcast)) ? str.substring(0, str.indexOf("@")) : str;
    }

    private static String GetType(String str) {
        String type;
        if (str.contains(Constant.gid)) type = "G";
        else if (str.contains(Constant.broadcast)) type = "B";
        else if (str.contains(Constant.jid)) type = "C";
        else if (str.contains(Constant.statusjid)) type ="ST";
        else type = "C";

        return type;
    }

    public static boolean HideCR(String jid, int n) {
        String s1 = "hidecr";
        Log.d(Constant.pref, s1);
        String s = strip(jid);
        if (n == 1)
            return getPrivacyB(GetType(jid) + Constant.crecord) ||
                    getPrivacyB(s + Constant.crecord);

        else
            return getPrivacyB(GetType(jid) + Constant.ccompose) ||
                    getPrivacyB(s + Constant.ccompose);
    }

    public static boolean HidePlay(String jab) {
        String s = "hideplay";
        Log.d(Constant.pref, s);
        String jid = strip(jab);

        return getPrivacyB(GetType(jab) + Constant.cplay) ||
                getPrivacyB(jid + Constant.cplay);

    }

    public static int HideForward(int i) {
        String s = "hidefwd";
        Log.d(Constant.pref, s);
        return (!getPrivacyB(Constant.hf) || i <= 0) ? i : i - 1;
    }

    public static boolean HideReceipt(String jab) {
        String s = "hidereceipt";
        Log.d(Constant.pref, s);
        String jid = strip(jab);

        return getPrivacyB(GetType(jab) + Constant.creceipt) ||
                getPrivacyB(jid + Constant.creceipt);

    }

    public static boolean HideSeen() {
        String s = "hideseen";
        Log.d(Constant.pref, s);
        return getPrivacyB(Constant.hs);
    }

    public static boolean HideStatus(String jab) {
        String s = "hidestatus";
        Log.d(Constant.pref, s);
        String jid = strip(jab);
        if (getPrivacyB(jid))
            return !getPrivacyB(Constant.hst);
        else
            return !getPrivacyB(jid + Constant.cstatus);

    }

    public static boolean ViewOnce(String jab)
    {
        String s = "viewonce";
        Log.d(Constant.pref, s);
        String jid = strip(jab);

        return getPrivacyB(GetType(jid) + Constant.cviewonce) ||
                getPrivacyB(jid + Constant.cviewonce);

    }

    static Intent OpenChat(String str, Context homeActivity) {
        try {
            return new Intent(homeActivity, MainActivity.class).putExtra("jid", str).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } catch (Exception e) {
            return null;
        }
    }

    public static void addMenu(Activity homeActivity, MenuItem menuItem) {
        if (homeActivity instanceof MainActivity) {
            if (menuItem.getItemId() == getID(homeActivity, "privacy")) {
                homeActivity.startActivity(new Intent(homeActivity, PrivacyActivity.class));
            } else if (menuItem.getItemId() == getID(homeActivity, "dnd")) {
                setdnd(!dnd());
                Toast.makeText(homeActivity, getString(homeActivity, "dndt"), Toast.LENGTH_SHORT).show();
            } else if (menuItem.getItemId() == getID(homeActivity, "chat")) {
                EditText editText = new EditText(homeActivity);
                AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
                builder.setTitle(getString(homeActivity, "newchat"))
                        .setMessage(getString(homeActivity, "enternum"))
                        .setView(editText)
                        .setPositiveButton(getString(homeActivity, "message"), new NewChat(editText, homeActivity))
                        .setNegativeButton(getString(homeActivity, "cancel"), new Cancel())
                        .create()
                        .show();
            }
        }
    }

    public static void updated(Context context) {
        if (isNetworkAvailable(context))
        {
            boolean b = false;
            int version = context.getSharedPreferences(Constant.upref,0).getInt(Constant.updated, 0);
            if (version == 0 || (Update.vercod(context) > version)) {
                b = true;
                context.getSharedPreferences(Constant.upref,0).edit().putInt(Constant.updated, Update.vercod(context)).apply();
            }

            if (b) {
                String url = Constant.php + getname(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final WebView view = new WebView(context);
                if (version == 0)
                    url += "_N";
                view.loadUrl(url);
                builder.setView(view)
                        .create()
                        .show();
            }
        }
    }

    public static void updateCheck(Context context)
    {
        long checked = context.getSharedPreferences(Constant.pref, 0).getLong(Constant.uchecked, 0);

        long diff = System.currentTimeMillis() - checked;
        long day = (12 * 60 * 60 * 1000);
        boolean check = diff > day;
        long secs = day - diff;

        if (check && isNetworkAvailable(context))
        {
            Log.d(Constant.pref, Constant.ulogyes);
            new Update(context).execute();
            context.getSharedPreferences(Constant.pref, 0).edit().putLong(Constant.uchecked, System.currentTimeMillis()).apply();
        }
        else
            Log.d(Constant.pref, Constant.ulogno + secs);
    }

    static boolean isNetworkAvailable(Context context) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }
        catch (Exception ex) {
            return false;
        }
    }

    private static String getname(Context context) {
        return context.getSharedPreferences("com.whatsapp_preferences_light",0).getString("push_name","");
    }

    public static void setMenu(Activity homeActivity, Menu menu) {
        if (homeActivity instanceof MainActivity) {
            MenuItem menuItem = menu.add(1, getID(homeActivity, "dnd"), 0, dndstr(homeActivity));
            menuItem.setIcon(dndimg(homeActivity));
            menuItem.setShowAsAction(2);


            SubMenu subMenu = menu.addSubMenu(1, 0, 0, getString(homeActivity, "more"));
            subMenu.add(2, getID(homeActivity, "privacy"), 0, getString(homeActivity, "priv"));
            subMenu.add(2, getID(homeActivity, "chat"), 0, getString(homeActivity, "newchat"));
        }
    }

    public static void setStatusMenu(Menu menu, Context context)
    {
        menu.add(0, getID(context, "status_save"), 0, getString(context, "status"));
    }

    public static void onStatusClick(int itemID, Activity activity, String jid)
    {
        Status status = new Status();
        if (itemID == getID(activity, "status_save"))
            status.downloadStatus(activity, jid);
    }

    public static void Show(final ViewGroup viewGroup, Activity activity) {
        if (activity instanceof MainActivity)
            viewGroup.postDelayed(new DNDcheck(viewGroup, activity), 7);
    }

    public static boolean dnd() {
        return sharedPreferences.getBoolean(Constant.dnd, false);
    }

    private static void setdnd(boolean b) {
        sharedPreferences.edit().putBoolean(Constant.dnd, b).apply();
    }

    private static String dndstr(Context context) {
        if (dnd())
            return context.getString(getString(context, "enint"));
        else
            return context.getString(getString(context, "disint"));
    }

    private static Drawable dndimg(Context context) {
        if (dnd())
            return getDrawable(context, "signal_on");
        else
            return getDrawable(context, "signal_off");
    }

    public static void select(TextView textView) {
        textView.setTextIsSelectable(true);

    }

    private static int getID(Context context, String name) {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }

    static int getString(Context context, String name)
    {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    private static Drawable getDrawable(Context context, String name) {
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        Drawable drawable = ContextCompat.getDrawable(context, id);
        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.setColorFilter(new BlendModeColorFilter(-1, BlendMode.SRC_ATOP));
            } else {
                drawable.setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
            }
        }
        return drawable;
    }
}
