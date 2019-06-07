package com.example.walmartproducts.view;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.walmartproducts.model.ApiMart;
import com.example.walmartproducts.viewmodel.DataViewModel;

public class BaseFragment extends Fragment {
    DataViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = DataViewModel.getViewModel(getActivity());
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if( imageUrl == null || imageUrl.length() < 4 )
            return;
        String fullUrl = ApiMart.BASE_URL + imageUrl.substring(1);
        Glide.with(view.getContext())
                .load(fullUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    @BindingAdapter({"bind:text"})
    public static void setText(TextView view, String text) {
        if( view == null )
            return;
        if( text == null )
            view.setText("");

        else if( text.indexOf("</") >= 0 || text.indexOf(">") >= 0)
            view.setText(Html.fromHtml(text));
        else
            view.setText(text);
    }

}
