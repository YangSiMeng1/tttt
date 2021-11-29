package com.bawei.usercenter.model;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.model.IModel;
import com.bawei.net.RetrofitFactory;
import com.bawei.untils.ThreadUntils;
import com.bawei.usercenter.entity.TestUserEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.service.api.LoginApi;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserCenterModel implements IModel {

    /**
     * 登录方法
     * @param phoneNumber
     * @param pwd
     * @return
     */

    public MutableLiveData<TestUserEntity> login(String phoneNumber, String pwd){
        //模拟网络请求

        SystemClock.sleep(500);


        MutableLiveData<TestUserEntity> result = new MutableLiveData<>();

        LoginApi o = RetrofitFactory.getInstance().create(LoginApi.class);

        Observable<TestUserEntity> login = o.login(new TestUserEntity(111, "111", "111", "111", "111"));
        login.subscribe(new Observer<TestUserEntity>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( TestUserEntity entity) {
                result.setValue(entity);
            }

            @Override
            public void onError( Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        return result;


    }
}
