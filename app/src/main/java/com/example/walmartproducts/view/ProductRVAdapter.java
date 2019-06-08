package com.example.walmartproducts.view;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.walmartproducts.R;
import com.example.walmartproducts.databinding.ProductItemBinding;
import com.example.walmartproducts.model.Product;
import com.example.walmartproducts.viewmodel.DataViewModel;

import java.util.List;

/*
 * ProductRVAdapter.java : RecyclerView adapter class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ProductVH> {
    private DataViewModel mViewModel;
    Fragment mParent;

    // Constructor
    public ProductRVAdapter(DataViewModel viewModel, Fragment parent) {
        this.mViewModel = viewModel;
        this.mParent = parent;

        // Set List data Observer object to ViewModel
        mViewModel.getProducts().observe(this.mParent.getActivity(),
                (@Nullable final List<Product> products) -> this.notifyDataSetChanged());
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
    }

    // Reuse views
    public static class ProductVH extends RecyclerView.ViewHolder {
        public ProductItemBinding binding;

        public ProductVH(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Set item index number to binding object
        public void bind(int index) {
            binding.setIndex(index);
            binding.executePendingBindings();
        }
    }

}
