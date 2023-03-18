package org.teamb58.whatsapp;

import android.content.DialogInterface;

public class Cancel implements DialogInterface.OnClickListener {

    public void onClick(DialogInterface dialogInterface, int i2) {
        dialogInterface.cancel();
    }
}
