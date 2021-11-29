package com.bawei.untils;

import android.widget.Toast;

import com.bawei.common.MyApplication;

public class MsgUtils {
    private static volatile MsgUtils msgUtils;
    private MsgUtils(){};


    public static MsgUtils getInstance() {
        if (msgUtils==null){
            synchronized (MsgUtils.class){
                if (msgUtils==null){
                    msgUtils = new MsgUtils();
                }
            }
        }


        return msgUtils;
    }


    /**
     * 提示消息
     * @param msg
     */
    public void showMsg(String msg){
        Toast.makeText(MyApplication.getAppContext(), msg , Toast.LENGTH_SHORT).show();
    }

}
