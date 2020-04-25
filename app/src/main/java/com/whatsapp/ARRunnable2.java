package com.whatsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.widget.TextView;

import com.b58works.whatsapp.R;

public class ARRunnable2 implements Runnable {

    private final Context context;
    private final TextView textView;

    private static final Object a = new Object();
    private static TypedValue b1;

    ARRunnable2(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public void run() {
        textView.setCompoundDrawablesWithIntrinsicBounds(c(context, R.drawable.message_got_receipt_revoked), (Drawable) null, (Drawable) null, (Drawable) null);
    }

    private static Drawable c(Context context, int i) {
        int i2;
        int i22 = Build.VERSION.SDK_INT;
        if (i22 >= 21) {
            return context.getDrawable(i);
        }
        if (i22 >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (a) {
            if (b1 == null) {
                b1 = new TypedValue();
            }
            context.getResources().getValue(i, b1, true);
            i2 = b1.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }

}
