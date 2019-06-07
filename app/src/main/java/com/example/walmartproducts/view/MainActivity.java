package com.example.walmartproducts.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.walmartproducts.R;

/*
 * MainActivity.java : Main activity class.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag", "MainActivity - onCreate()");
    }

}
