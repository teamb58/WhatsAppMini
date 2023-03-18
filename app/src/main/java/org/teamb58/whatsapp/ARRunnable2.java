package org.teamb58.whatsapp;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

class ARRunnable2 implements Runnable {

    private final Context context;
    private final TextView textView;

    private final boolean revoked;

    public ARRunnable2(Context context, TextView textView, boolean revoked) {
        this.context = context;
        this.textView = textView;
        this.revoked = revoked;
    }

    @Override
    public void run() {
        if (revoked)
            textView.setCompoundDrawablesWithIntrinsicBounds(c(context), null, null, null);
        else
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    private Drawable c(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, getDrawble(context));

        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.setColorFilter(new BlendModeColorFilter(Color.parseColor("#cc0000"), BlendMode.SRC_ATOP));
            }
            else
                drawable.setColorFilter(Color.parseColor("#cc0000"), PorterDuff.Mode.SRC_ATOP);
        }
        return drawable;
    }

    private int getDrawble(Context context) {
        return context.getResources().getIdentifier("message_got_receipt_revoked", "drawable", context.getPackageName());
    }

}
