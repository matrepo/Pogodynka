package com.mateusz.pogodynka;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MyWebService {

    @GET("/weather?units=metric")
    void getData(@Query("APPID") String appiid, @Query("q") String q, Callback<DataBody> pResponse);

}
