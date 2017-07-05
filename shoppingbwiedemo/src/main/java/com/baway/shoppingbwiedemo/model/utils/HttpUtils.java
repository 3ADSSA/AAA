package com.baway.shoppingbwiedemo.model.utils;

import com.baway.shoppingbwiedemo.BuildConfig;
import com.baway.shoppingbwiedemo.view.iview.Api;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpUtils {

    //POST请求方式
    public static void postRequestData(String uri, String link_ramus, Map<String,String> map, Observer<String> observer){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.postRequest(link_ramus,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }

    //GET请求方式
    public static void getRequestData(String uri, String link_ramus, Map<String,String> map, Observer<String> observer){

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10,TimeUnit.SECONDS);

        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        api.getRequest(link_ramus,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
