package x;

import android.content.DialogInterface;

class Cancel implements DialogInterface.OnClickListener {

    public void onClick(DialogInterface dialogInterface, int i2) {
        dialogInterface.cancel();
    }

}
