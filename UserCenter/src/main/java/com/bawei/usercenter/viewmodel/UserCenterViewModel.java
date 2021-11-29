package com.bawei.usercenter.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.usercenter.entity.TestUserEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.repository.UserCenterRepository;

public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {
    //页面数据源
   public MutableLiveData<UserEntity> userEntityMutableLiveData = new MutableLiveData<>();


    public UserCenterViewModel(LifecycleOwner _owner) {
        super(_owner);
    }

    @Override
    protected UserCenterRepository createRepository() {
        return new UserCenterRepository();
    }

    @Override
    protected void initRes() {

    }

    @Override
    protected void releaseRes() {

    }

    public MutableLiveData<TestUserEntity> login(String phoneNumber, String pwd){

        return mRepository.login(phoneNumber, pwd);

    }
}
