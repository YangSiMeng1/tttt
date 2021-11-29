package com.bawei.mvvmcore.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.untils.MsgUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEnv();
    }

    protected abstract void initEnv();

    /**
     * 跳转到指定activity
     * @param target
     */
    protected void jumpToActivity(Class<?> target){
        this.startActivity(new Intent(this,target));
    }

    /**
     * 携带参数跳转
     * @param target
     * @param bundle
     */
    protected void jumpToActivity(Class<?> target,Bundle bundle){
        Intent intent = new Intent();
        intent.putExtra("parms",bundle);
        this.startActivity(intent);
    }

    /**
     * 吐司
     * @param msg
     */
    protected void showMsg(String msg){
        MsgUtils.getInstance().showMsg(msg);
    }



}
