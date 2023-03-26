package org.teamb58.whatsapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class DNDcheck implements Runnable {
    private final ViewGroup viewGroup;
    private final Context context;

    DNDcheck(ViewGroup viewGroup, Context context) {
        this.viewGroup = viewGroup;
        this.context = context;
    }

    @Override
    public void run() {
        String text = context.getString(Main.getString(context, "dndstatus"));
        View childAt = viewGroup.getChildAt(0);
        if (childAt instanceof TextView) {
            TextView textView = (TextView)childAt;
            if(Main.dnd()) {
                if (!textView.getText().toString().contains(text))
                    textView.setText(textView.getText() + text);
            }
        }
    }
}
