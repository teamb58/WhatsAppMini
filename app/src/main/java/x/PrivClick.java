package x;

import android.content.Context;
import android.preference.Preference;
import android.widget.Toast;

public class PrivClick implements Preference.OnPreferenceClickListener {
    private Context context;

    public PrivClick(Context applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        Main.sharedPreferences.edit().clear().apply();
        Toast.makeText(context, Constant.resett, Toast.LENGTH_SHORT).show();
        return true;
    }
}
