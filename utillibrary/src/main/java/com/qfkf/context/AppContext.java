package com.qfkf.context;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.qfkf.util.ShareUtil;


public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化共享参数
        ShareUtil.init(this);
        /**
         * activeandroid初始化
         */
        ActiveAndroid.initialize(this);

//        SDKInitializer.initialize(getApplicationContext());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
