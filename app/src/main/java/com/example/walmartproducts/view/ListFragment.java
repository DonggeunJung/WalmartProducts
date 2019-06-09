package com.example.walmartproducts.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.walmartproducts.R;
import com.example.walmartproducts.databinding.FragmentListBinding;

import static android.content.ContentValues.TAG;

/*
 * ListFragment.java : Product list fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class ListFragment extends BaseFragment {
    boolean mMultiPanel = false;

    // When Fragment view is created, load layout file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Request Product list to server
        mViewModel.reqMart();
        // Save self fragment object as a member variable
        FragmentListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        // Init RecyclerView adapter & Request Product list to server
        initList(binding);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Check whether multiple panel mode
        initBodyFragment();
    }

    // Check whether multiple panel mode
    private void initBodyFragment() {
        // Get Body fragment from layout & save to member variable
        BodyFragment bodyF = (BodyFragment)getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragBody);
        // When 2nd panel is exist, it means multiple panel
        if( bodyF != null && bodyF.getView().getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
        }
    }

    // Init RecyclerView adapter & Request Product list to server
    protected void initList(FragmentListBinding binding) {
        // Init RecyclerView adapter
        ProductRVAdapter rvAdapter = new ProductRVAdapter(mViewModel, this);
        binding.rv.setAdapter( rvAdapter );
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        // Show Item Divider on RecyclerView
        binding.rv.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        // Lazy loading : When scroll to end, request next page of list to server
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!binding.rv.canScrollVertically(1)) {
                    Log.i(TAG, "End of list");
                    mViewModel.reqMart();
                }
            }
        });
    }

    // Product RecyclerView item selection event
    public void onItemSelected(int position) {
        // When single panel mode, switch to 2nd fragment
        if( mMultiPanel == false )
            switch2BodyFragment();

        // Set select particular item of Product list
        mViewModel.setSelProduct(position);
    }

    // Switch Body fragment
    private void switch2BodyFragment() {
        BodyFragment bodyF = new BodyFragment();

        // Change fragment on container layout(ViewGroup)
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.panelList, bodyF)
                .addToBackStack(null)
                .commit();
    }

}
