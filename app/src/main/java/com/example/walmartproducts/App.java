package com.example.walmartproducts;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.example.walmartproducts.viewmodel.DataViewModel;

public class App extends Application {
    static DataViewModel mViewModel;

    public static DataViewModel getViewModel(FragmentActivity activity) {
        if( mViewModel == null )
            mViewModel = ViewModelProviders.of(activity).get(DataViewModel.class);
        return mViewModel;
    }
}
