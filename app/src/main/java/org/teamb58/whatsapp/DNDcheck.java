package org.teamb58.whatsapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class DNDcheck implements Runnable {
    private final ViewGroup viewGroup;

    DNDcheck(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void run() {
        View childAt = viewGroup.getChildAt(0);
        if (childAt instanceof TextView) {
            TextView textView = (TextView)childAt;
            if(Main.dnd()) {
                if (!textView.getText().toString().contains(Constant.dndstatus))
                    textView.setText(textView.getText() + Constant.dndstatus);
            }
        }
    }
}
