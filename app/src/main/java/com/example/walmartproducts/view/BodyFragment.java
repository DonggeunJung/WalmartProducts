package com.example.walmartproducts.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.walmartproducts.model.ApiMart;
import com.example.walmartproducts.R;
import com.example.walmartproducts.databinding.FragmentBodyBinding;
/*
 * BodyFragment.java : Detail infomation fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class BodyFragment extends BaseFragment {
    FragmentBodyBinding mBinding;

    // When Fragment view is created, load layout file
    // Bind view with ViewModel
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_body, container, false);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

}
