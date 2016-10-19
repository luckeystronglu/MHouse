package com.qf.luckey.mhouse;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.qfkf.util.ShareUtil;


public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化共享参数
        ActiveAndroid.initialize(this);
        ShareUtil.init(this);
        /**
         * activeandroid初始化
         */


//        SDKInitializer.initialize(getApplicationContext());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
