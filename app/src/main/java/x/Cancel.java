package x;

import android.content.DialogInterface;
import android.widget.Toast;

class Cancel implements DialogInterface.OnClickListener {

    private final int i;
    private Main main;

    Cancel(int i2) {
        this.i = i2;
        main = new Main();
    }

    public void onClick(DialogInterface dialogInterface, int i2) {
        if (this.i == 1) {
            remind();
        }
        dialogInterface.cancel();
    }

    private void remind() {
        Main.sharedPreferences.edit().putLong(Constant.remind, System.currentTimeMillis()).apply();
        Toast.makeText(main.context, Constant.remind_later, 0).show();
    }


}
