package com.whatsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class CustomSwitchPreference extends LinearLayout {

    private TextView title;
    private TextView subTitle;
    private ImageView icon;
    private SwitchCompat switchCompat;
    private boolean detach;

    private String key;

    private static final String APPLICATIONS="http://schemas.android.com/apk/res-auto";

    public interface SettingOnClickListener {
        void onClick(View view, boolean checked);

        void onCheckedChanged(View view, boolean checked);
    }

    public void setSettingOnClickListener(SettingOnClickListener settingOnClickListener) {
        this.settingOnClickListener = settingOnClickListener;
    }

    private SettingOnClickListener settingOnClickListener;


    public CustomSwitchPreference(Context context) {
        super(context);
        init();
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        String title = (String) getValueFromAttribute(attrs, "sw_title");
        String summary = (String) getValueFromAttribute(attrs, "sw_summary");
        key = (String) getValueFromAttribute(attrs, "sw_key");
        detach = attrs.getAttributeBooleanValue(APPLICATIONS, "sw_detach", false);
        Drawable icon = (Drawable) getValueFromAttribute(attrs, "sw_icon");

        init();

        setItemTitle(title);
        setSummary(summary);
        setIcon(icon);

        setChecked(key);
        switchCompat.setVisibility(VISIBLE);

        initEvent();
    }

    private Object getValueFromAttribute(AttributeSet attrs, String name) {
        int value = attrs.getAttributeResourceValue(APPLICATIONS, name, 0);

        if(name.equals("sw_icon"))
            return ContextCompat.getDrawable(getContext(), value);
        else if (name.equals("sw_key"))
            return attrs.getAttributeValue(APPLICATIONS, name);
        else
            return getContext().getResources().getString(value);
    }

    private void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (detach) {
                    switchCompat.setChecked(!switchCompat.isChecked());
                }

                if (settingOnClickListener != null) {
                    settingOnClickListener.onClick(v, switchCompat.isChecked());
                }
            }
        });


        switchCompat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (settingOnClickListener != null) {
                    settingOnClickListener.onCheckedChanged(CustomSwitchPreference.this,
                            switchCompat.isChecked());
                }
                setKeyStatus(key);
            }
        });
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(getResID("custom_switch_preference", "layout"), this);

        this.title = findViewById(getResID("pref_title", "id"));
        this.subTitle = findViewById(getResID("pref_subtitle", "id"));
        this.icon = findViewById(getResID("pref_icon", "id"));
        switchCompat = findViewById(getResID("pref_switch", "id"));
    }

    public void setItemTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            this.title.setVisibility(GONE);
        } else {
            if (this.title.getVisibility() == GONE) {
                this.title.setVisibility(VISIBLE);
            }
            this.title.setText(title);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == null) {
            this.icon.setVisibility(GONE);
        } else {
            if (this.icon.getVisibility() == GONE) {
                this.icon.setVisibility(VISIBLE);
            }
            this.icon.setImageDrawable(drawable);
        }
    }

    public void setSummary(String summary) {
        if (TextUtils.isEmpty(summary)) {
            subTitle.setVisibility(GONE);
        } else {
            if (subTitle.getVisibility() == GONE) {
                subTitle.setVisibility(VISIBLE);
            }
            subTitle.setText(summary);
        }

    }

    public void setChecked(String key) {
        switchCompat.setChecked(getKeyStatus(key));
    }

    public boolean isChecked() {
        return switchCompat.isChecked();
    }

    private void setKeyStatus(String key)
    {
        WhatsApp.sharedPreferences.edit().putBoolean(key, isChecked()).apply();
    }

    private boolean getKeyStatus(String key)
    {
        return WhatsApp.sharedPreferences.getBoolean(key, false);
    }

    private int getResID(String name, String type)
    {
        return getResources().getIdentifier(name, type, getContext().getPackageName());
    }

}
