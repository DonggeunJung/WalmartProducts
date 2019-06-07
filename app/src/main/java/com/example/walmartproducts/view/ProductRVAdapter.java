package com.example.walmartproducts.view;

import android.arch.lifecycle.Observer;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.walmartproducts.R;
import com.example.walmartproducts.databinding.ProductItemBinding;
import com.example.walmartproducts.model.ApiMart;
import com.example.walmartproducts.model.Product;
import com.example.walmartproducts.viewmodel.DataViewModel;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ProductVH> {
    private DataViewModel mViewModel;
    Fragment mParent;
    RecyclerView.Adapter<ProductRVAdapter.ProductVH> mAdapter;

    // Constructor
    public ProductRVAdapter(DataViewModel viewModel, Fragment parent) {
        this.mViewModel = viewModel;
        //viewModel.setRVAdapter(this);
        this.mParent = parent;
        mAdapter = this;

        // make Product list Observer object
        final Observer<List<Product>> productsObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                // When Product list is changed update RecyclerView
                mAdapter.notifyDataSetChanged();
            }
        };

        // Send Observer object to ViewModel
        viewModel.getProducts().observe(this.mParent, productsObserver);
    }

    // Return list items count
    @Override
    public int getItemCount() {
        // Get the Product items count
        List<Product> products = mViewModel.getProducts().getValue();

        // When the data object is not exist return 0
        if( products == null )
            return 0;
        return products.size();
    }

    // Make ViewHolder & View binding object
    @Override
    public ProductRVAdapter.ProductVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Get the view binding object of custom list item layout
        LayoutInflater inflater = LayoutInflater.from(mParent.getContext());
        ProductItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.product_item, viewGroup, false);
        // Set the Lifecycle Owner of View binding to fragment
        binding.setLifecycleOwner(mParent);
        // Set ViewModel object to binding object as a variable
        binding.setViewModel(mViewModel);
        binding.setListFragment((ListFragment)mParent);

        // Make ViewHolder object
        return new ProductRVAdapter.ProductVH(binding);
    }

    // When ViewHolder is binded set data to binding object
    @Override
    public void onBindViewHolder(ProductRVAdapter.ProductVH viewHolder, int position) {
        // Set item index number to binding object
        viewHolder.bind(position);

        // Show shortDescription to TextView
        List<Product> products = mViewModel.getProducts().getValue();
        Product product = products.get(position);
        String shortDescription = product.getShortDescription();
        if( shortDescription == null || shortDescription.length() < 8 )
            viewHolder.binding.shortDescription.setText("");
        else
            viewHolder.binding.shortDescription.setText(Html.fromHtml(shortDescription));
    }

    // Reuse views
    public static class ProductVH extends RecyclerView.ViewHolder {
        public ProductItemBinding binding;

        public ProductVH(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int index) {
            binding.setIndex(index);
            binding.executePendingBindings();
        }
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

}
