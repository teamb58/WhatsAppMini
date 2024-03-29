package org.teamb58.whatsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;


class NewChat implements DialogInterface.OnClickListener {
    private final EditText editText;
    private final Context homeActivity;

    NewChat(EditText editText2, Context homeActivity2) {
        this.editText = editText2;
        this.homeActivity = homeActivity2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String replace = this.editText.getText().toString().trim().replace(" ", "");
        String str = replace + "@s.whatsapp.net";
        if (replace.contains("-")) {
            str = replace + "@g.us";
        }
        this.homeActivity.startActivity(Main.OpenChat(str, homeActivity));
    }

}
