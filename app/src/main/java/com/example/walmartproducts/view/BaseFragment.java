package com.example.walmartproducts.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.walmartproducts.App;
import com.example.walmartproducts.viewmodel.DataViewModel;

public class BaseFragment extends Fragment {
    DataViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = App.getViewModel(getActivity());
    }

}
