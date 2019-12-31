package com.superman.app.hotfixloadclass;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.superman.app.hotfixloadclass.utils.FixDexUtils;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        FixDexUtils.loadFixedDex(this);
    }
}
