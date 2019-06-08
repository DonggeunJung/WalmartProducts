package com.example.walmartproducts.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.walmartproducts.model.ApiMart;
import com.example.walmartproducts.model.Mart;
import com.example.walmartproducts.model.Product;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
 * DataViewModel.java : ViewModel class. Manage data & request to server
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class DataViewModel extends ViewModel {
    private MutableLiveData<Mart> mMart;
    private MutableLiveData<List<Product>> mProducts;
    private MutableLiveData<Product> mSelProduct;

    @Inject
    ApiMart mApi;
    static DataViewModel mViewModel;

    // Create & return self instance
    public static DataViewModel getViewModel(FragmentActivity activity) {
        if( mViewModel == null )
            mViewModel = ViewModelProviders.of(activity).get(DataViewModel.class);
        return mViewModel;
    }

    // Constructor - Inject Retrofit API object
    public DataViewModel() {
        ApiComponent component = DaggerApiComponent.builder().build();
        component.inject(this);
    }

    public MutableLiveData<Mart> getMart() {
        if (mMart == null) {
            mMart = new MutableLiveData<Mart>();
        }
        return mMart;
    }

    public MutableLiveData<List<Product>> getProducts() {
        if (mProducts == null) {
            mProducts = new MutableLiveData<List<Product>>();
        }
        return mProducts;
    }

    public MutableLiveData<Product> getSelProduct() {
        if (mSelProduct == null) {
            mSelProduct = new MutableLiveData<Product>();
        }
        return mSelProduct;
    }

    //===============================================

    // Request Book data list to server
    public void reqMart() {
        int pageNumber = 0;
        Mart mart = getMart().getValue();
        if( mart != null )
            pageNumber = mart.getPageNumber();

        Call<Mart> call = mApi.getMart(pageNumber+1, 15);
        call.enqueue(new Callback<Mart>() {

            @Override
            public void onResponse(Call<Mart> call, Response<Mart> response) {
                // When completed, get Mart data & save
                Mart mart = response.body();
                getMart().setValue(mart);

                // Get Products list data & save
                List<Product> products = mart.getProducts();
                addProducts(products);
            }

            @Override
            public void onFailure(Call<Mart> call, Throwable t) {
                String message = t.getMessage();
            }
        });
    }

    // Set select particular item of Product list
    public void setSelProduct(int index) {
        List<Product> products = getProducts().getValue();
        if( products == null || products.size() <= index )
            return;

        getSelProduct().setValue( products.get(index) );
    }

    // Save new product list in Array
    public void addProducts(List<Product> newProducts) {
        if( newProducts == null || newProducts.size() < 1 ) return;
        // When this is the 1st data
        List<Product> products = getProducts().getValue();
        if( products == null ) {
            // Save new list to Array
            getProducts().setValue(newProducts);
            // Show 1st item's detail data on body Fragment
            setSelProduct(0);
            return;
        }

        // When this is not 1st data
        for(Product product : newProducts ) {
            // Add new list to previous Array
            products.add(product);
        }
        //Log.d("tag", "ViewModel - addProducts(): " + newProducts.size());
        // Send event to View (= RecyclerView Adapter)
        getProducts().postValue(products);
    }

}
