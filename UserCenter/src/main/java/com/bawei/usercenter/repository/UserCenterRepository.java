package com.bawei.usercenter.repository;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.repository.BaseRepository;
import com.bawei.untils.ThreadUntils;
import com.bawei.usercenter.entity.TestUserEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.UserCenterModel;

public class UserCenterRepository extends BaseRepository<UserCenterModel> {
    @Override
    protected UserCenterModel createModel() {
        return new UserCenterModel();
    }

    /**
     * 用户登录  方法
     * @param phoneNumber
     * @param pwd
     * @return
     */
    public MutableLiveData<TestUserEntity> login(String phoneNumber, String pwd){

        return mModel.login(phoneNumber, pwd);
    }


}
