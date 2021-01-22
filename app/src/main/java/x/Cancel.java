package x;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Cancel implements DialogInterface.OnClickListener {

    private final int i;
    private Context context;

    Cancel(int i2) {
        this.i = i2;
    }

    public Cancel(Context context, int i)
    {
        this.i = i;
        this.context = context;
    }

    public void onClick(DialogInterface dialogInterface, int i2) {
        if (this.i == 1) {
            remind();
        }
        dialogInterface.cancel();
    }

    private void remind() {
        Main.sharedPreferences.edit().putLong(Constant.remind, System.currentTimeMillis()).apply();
        Main.sharedPreferences.edit().putBoolean(Constant.uaval,true).apply();

        Toast.makeText(this.context, Constant.remind_later, Toast.LENGTH_SHORT).show();
    }


}
