package com.example.walmartproducts.viewmodel;

import com.example.walmartproducts.model.ApiMart;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
/*
 * ApiComponent.java : Dagger class for Retrofit object injection
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    ApiMart provideApi() {
        // Make Retrofit API object & return
        return ApiMart.retrofit.create(ApiMart.class);
    }
}
