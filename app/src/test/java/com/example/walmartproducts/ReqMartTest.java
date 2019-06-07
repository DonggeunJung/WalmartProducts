package com.example.walmartproducts;

import com.example.walmartproducts.model.ApiMart;
import com.example.walmartproducts.model.Mart;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;

public class ReqMartTest extends BaseRetrofitTest {

    @Test
    public void login_Success() {
        // Make Retrofit API object
        ApiMart api = getApiMart();
        Call<Mart> call = api.getMart(1, 10);

        try {
            // Request School data list to server
            Response<Mart> response = call.execute();
            // Get the School simple data list.
            Mart mart = response.body();
            if( mart == null )
                return;

            // Check whether enough items exist in School list
            assertTrue(response.isSuccessful()
                    && mart.getProducts().size() >= 10 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
