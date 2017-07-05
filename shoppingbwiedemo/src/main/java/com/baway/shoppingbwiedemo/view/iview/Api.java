package com.baway.shoppingbwiedemo.view.iview;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {

    @FormUrlEncoded
    @POST
    Observable<String> postRequest(@Url String link_ramus, @FieldMap Map<String,String> map);

    @GET
    Observable<String> getRequest(@Url String link_ramus, @QueryMap Map<String,String> map);

}
