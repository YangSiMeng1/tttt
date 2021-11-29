package com.bawei.usercenter.model.service.api;

import com.bawei.usercenter.entity.TestUserEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("api/User/login")
    Observable<TestUserEntity> login(@Body TestUserEntity entity);
}
