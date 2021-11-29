package com.bawei.common;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }


    /**
     * 获取上下文方法
     * @return
     */
    public static Context getAppContext(){
        return context;
    }
}
