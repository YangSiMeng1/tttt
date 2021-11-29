package com.bawei.usercenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.net.RetrofitFactory;
import com.bawei.untils.MsgUtils;
import com.bawei.usercenter.BR;
import com.bawei.usercenter.R;
import com.bawei.usercenter.databinding.ActivityLoginBinding;
import com.bawei.usercenter.entity.TestUserEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.service.api.LoginApi;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityLoginBinding> {



    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.user,mViewModel.userEntityMutableLiveData);
        mMap.put(BR.owner,this);
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public void onLogin(View view){


        String phoneNumber = mViewModel.userEntityMutableLiveData.getValue().getPhoneNumber();
        String pwd = mViewModel.userEntityMutableLiveData.getValue().getPwd();
        Log.i("123", "onLogin: "+phoneNumber+pwd);

        mViewModel.login(phoneNumber,pwd).observe(this, new Observer<TestUserEntity>() {
            @Override
            public void onChanged(TestUserEntity entity) {
                Log.i("123", "onChanged: "+entity.getUsername());
            }
        });
    }

    @Override
    protected void initEnv() {
        mViewModel.userEntityMutableLiveData.setValue(new UserEntity());
    }
}