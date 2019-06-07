package com.example.walmartproducts.viewmodel;

import com.example.walmartproducts.model.ApiMart;
import javax.inject.Singleton;

import dagger.Component;
/*
 * ApiComponent.java : Dagger interface for Retrofit object injection
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
@Singleton
@Component(modules = {ApiModule.class})
interface ApiComponent {
    ApiMart provideApi();

    void inject(DataViewModel viewModel);
}
