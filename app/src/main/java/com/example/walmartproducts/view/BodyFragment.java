package com.example.walmartproducts.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.walmartproducts.R;
import com.example.walmartproducts.databinding.FragmentBodyBinding;
/*
 * BodyFragment.java : Detail infomation fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class BodyFragment extends BaseFragment {

    // When Fragment view is created, load layout file & Bind view with ViewModel
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Bind view with ViewModel
        FragmentBodyBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_body, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

}
