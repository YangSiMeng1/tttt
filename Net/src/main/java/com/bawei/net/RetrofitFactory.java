package com.bawei.net;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Button;

import com.bawei.net.common.Constanct;
import com.bawei.net.protocol.resp.TokenRespEntity;
import com.bawei.net.server.api.TokenApi;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory = null;

    private Retrofit retrofit = null;

    private RetrofitFactory(){
        retrofit = createRetrofit();
    }

    /**
     * 创建retrofit实例
     * @return
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://82.156.178.182:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               // .addCallAdapterFactory(LiveDataCallAdapterFactory().create())
                .client(createOkHttpClient())
                .build();


    }

    /**
     * 创建okhttp实例
     * @return
     */

    private OkHttpClient createOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(Constanct.TIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(Constanct.TIMEOUT,TimeUnit.MINUTES)
                .writeTimeout(Constanct.TIMEOUT,TimeUnit.MINUTES)
                .addNetworkInterceptor(createOkHttpInterceptor())
                .addInterceptor(createTokenInterceptor())
                .addInterceptor(createHeadesInterceptor())
                .build();

        return client;
    }

    /**
     * 创建请求头拦截器
     * @return
     */
    private Interceptor createHeadesInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
    }

    /**
     * 处理Token拦截器
     * @return
     */
    private Interceptor createTokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                if (proceed.code()==401){
                    //获取token
                    String token =  doTokenTask();

                    Request newRequest = request.newBuilder().addHeader("Authorization", "bearer " + token).build();

                    return chain.proceed(newRequest);
                }

                return proceed;
            }


        };
    }

    /**
     * 处理token请求
     * @return
     */
    private String doTokenTask() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> password = tokenApi.getToken("password", "341de11517517819a16213218f10712d1df1fa1221471591", "");

        try {
            retrofit2.Response<TokenRespEntity> execute = password.execute();
            if (execute!=null&&execute.body()!=null){
                return execute.body().getAccess_token();
            }
        } catch (IOException e) {
            Log.i("123", e.getMessage());
        }

        return "";

    }


    /**
     * 创建日志拦截器
     * @return
     */
    private Interceptor createOkHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    ;

    public static RetrofitFactory getInstance() {
        if (retrofitFactory==null){
            synchronized (RetrofitFactory.class){
                if (retrofitFactory==null){
                    retrofitFactory = new RetrofitFactory();
                }
            }
        }
        return retrofitFactory;
    }

    /**
     * 根据api接口获取对应实例
     * @param service
     * @param <T>
     * @return
     */


    public <T> T create(Class<?> service){
        return (T) retrofit.create(service);
    }


}
