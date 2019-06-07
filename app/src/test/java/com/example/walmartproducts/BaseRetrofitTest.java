package com.example.walmartproducts;

import com.example.walmartproducts.model.ApiMart;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitTest {

    // Make Retrofit Api object & return
    public static ApiMart getApiMart() {
        // Make Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiMart.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        // Make Retrofit API object
        ApiMart api = retrofit.create(ApiMart.class);
        return api;
    }
}
