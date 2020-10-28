package com.whatsapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SwitchCompat;

public class Privacy extends LinearLayout implements View.OnClickListener {
    private final Dialog dialog = new Dialog(getContext());
    private SharedPreferences.Editor edit;

    public Privacy(final Context context) {
        super(context);
        init();
    }

    public Privacy(final Context context, final AttributeSet set) {
        super(context, set);
        init();
    }

    private Boolean getSpecific(final String s, final String s2) {
        if (s.contains("g.us"))
            return WhatsApp.getPrivacyB("G" + s2);
        else
            return WhatsApp.getPrivacyB("C" + s2);

    }

    @SuppressLint("CommitPrefEdits")
    private void init() {
        edit = WhatsApp.sharedPreferences.edit();
        this.setOnClickListener( this);
    }

    public void onClick(final View view) {
        this.dialog.setTitle( "Set Custom Privacy");
        this.dialog.setContentView(getLayout());
        final SwitchCompat switchCompat =  this.dialog.findViewById(getID("tb"));
        final SwitchCompat switchCompat2 = this.dialog.findViewById(getID("tb1"));
        final SwitchCompat switchCompat3 = this.dialog.findViewById(getID("tb2"));
        final SwitchCompat switchCompat4 = this.dialog.findViewById(getID("tb3"));
        final SwitchCompat switchCompat5 = this.dialog.findViewById(getID("tb4"));
        final SwitchCompat switchCompat6 = this.dialog.findViewById(getID("tb5"));
        final SwitchCompat switchCompat7 = this.dialog.findViewById(getID("tb6"));
        final SwitchCompat switchCompat8 = this.dialog.findViewById(getID("tb7"));
        //final SwitchCompat switchCompat9 = this.dialog.findViewById(R.id.tb8);
        switchCompat.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber)));
        switchCompat.setOnCheckedChangeListener(new MainSwitch(switchCompat2, switchCompat3, switchCompat4, switchCompat5, switchCompat6, switchCompat7, switchCompat8));
        if (!switchCompat.isChecked()) {
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRead",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRead"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideReceipt",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideReceipt"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideCompose",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideCompose"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRecord",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRecord"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HidePlay",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HidePlay"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideStatus",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideStatus"));
            this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_AR",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_AR"));
            //this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideForward",  this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideForward"));
        }
        switchCompat2.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideRead"));
        switchCompat2.setOnCheckedChangeListener(new Read());
        switchCompat3.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideReceipt"));
        switchCompat3.setOnCheckedChangeListener(new Receipt());
        switchCompat4.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideCompose"));
        switchCompat4.setOnCheckedChangeListener(new Compose());
        switchCompat5.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideRecord"));
        switchCompat5.setOnCheckedChangeListener(new Record());
        switchCompat6.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HidePlay"));
        switchCompat6.setOnCheckedChangeListener(new Play());
        switchCompat7.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideStatus"));
        switchCompat7.setOnCheckedChangeListener(new Status());
        switchCompat8.setChecked(WhatsApp.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_AR"));
        switchCompat8.setOnCheckedChangeListener(new Revoke());
        /*switchCompat9.setChecked(Privacy.getPrivacyB(ARRunnable.strip(WhatsApp.jabber) + "_HideForward"));
        switchCompat9.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideForward", b);
                Privacy.this.edit.apply();
            }
        });*/
        if (!switchCompat.isChecked()) {
            switchCompat2.setEnabled(false);
            switchCompat3.setEnabled(false);
            switchCompat4.setEnabled(false);
            switchCompat5.setEnabled(false);
            switchCompat6.setEnabled(false);
            switchCompat7.setEnabled(false);
            switchCompat8.setEnabled(false);
            //switchCompat9.setEnabled(false);
        }
        this.dialog.show();
    }

    private class MainSwitch implements CompoundButton.OnCheckedChangeListener {
        private final SwitchCompat switchCompat2;
        private final SwitchCompat switchCompat3;
        private final SwitchCompat switchCompat4;
        private final SwitchCompat switchCompat5;
        private final SwitchCompat switchCompat6;
        private final SwitchCompat switchCompat7;
        private final SwitchCompat switchCompat8;

        public MainSwitch(SwitchCompat switchCompat2, SwitchCompat switchCompat3, SwitchCompat switchCompat4, SwitchCompat switchCompat5, SwitchCompat switchCompat6, SwitchCompat switchCompat7, SwitchCompat switchCompat8) {
            this.switchCompat2 = switchCompat2;
            this.switchCompat3 = switchCompat3;
            this.switchCompat4 = switchCompat4;
            this.switchCompat5 = switchCompat5;
            this.switchCompat6 = switchCompat6;
            this.switchCompat7 = switchCompat7;
            this.switchCompat8 = switchCompat8;
        }

        public void onCheckedChanged(final CompoundButton compoundButton, final boolean enabled) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber), enabled);
            Privacy.this.edit.apply();
            switchCompat2.setEnabled(enabled);
            switchCompat3.setEnabled(enabled);
            switchCompat4.setEnabled(enabled);
            switchCompat5.setEnabled(enabled);
            switchCompat6.setEnabled(enabled);
            switchCompat7.setEnabled(enabled);
            switchCompat8.setEnabled(enabled);
            //switchCompat9.setEnabled(enabled);
            if (enabled) {
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRead",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRead"));
                switchCompat2.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRead"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideReceipt",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideReceipt"));
                switchCompat3.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideReceipt"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideCompose",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideCompose"));
                switchCompat4.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideCompose"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRecord",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRecord"));
                switchCompat5.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideRecord"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HidePlay",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HidePlay"));
                switchCompat6.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HidePlay"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideStatus",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideStatus"));
                switchCompat7.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideStatus"));
                Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_AR",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_AR"));
                switchCompat8.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_AR"));
                //Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideForward",  Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideForward"));
                //switchCompat9.setChecked(Privacy.this.getSpecific(ARRunnable.strip(WhatsApp.jabber), "_HideForward"));
                Privacy.this.edit.apply();
            }
        }
    }

    private class Read implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRead", b);
            Privacy.this.edit.apply();
        }
    }

    private class Receipt implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideReceipt", b);
            Privacy.this.edit.apply();
        }
    }

    private class Compose implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideCompose", b);
            Privacy.this.edit.apply();
        }
    }

    private class Record implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideRecord", b);
            Privacy.this.edit.apply();
        }
    }

    private class Play implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HidePlay", b);
            Privacy.this.edit.apply();
        }
    }

    private class Status implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_HideStatus", b);
            Privacy.this.edit.apply();
        }
    }

    private class Revoke implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
            Privacy.this.edit.putBoolean(ARRunnable.strip(WhatsApp.jabber) + "_AR", b);
            Privacy.this.edit.apply();
        }
    }

    private int getID(String name)
    {
        return getContext().getResources().getIdentifier(name, "id", getContext().getPackageName());
    }

    private int getLayout()
    {
        return getContext().getResources().getIdentifier("custom_privacy", "layout", getContext().getPackageName());
    }
}
