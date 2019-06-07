package com.example.walmartproducts.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
 * ApiMart.java : Retrofit API interface
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public interface ApiMart {

    // Make Retrofit object
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiMart.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();

    public static final String BASE_URL = "https://mobile-tha-server.firebaseapp.com/";

    @GET("walmartproducts/{pageNumber}/{pageSize}")
    Call<Mart> getMart(@Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize);

}
