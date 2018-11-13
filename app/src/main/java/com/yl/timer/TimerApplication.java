package com.yl.timer;

import android.app.Application;

import org.litepal.LitePal;

public class TimerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
