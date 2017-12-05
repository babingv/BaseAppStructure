package com.oclocksoftware.appstructure.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oclocksoftware.appstructure.ui.activity.BaseActivity;

/**
 * Created by babin on 12/5/2017.
 */

public abstract class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    protected View rootView;
    ProgressDialog progressDoalog;

    public BaseFragment() {
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    /**
     * Initialize Data Binding views
     */
    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * Toolbar initialization
     */
    protected void initToolbar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initViews(inflater, container, savedInstanceState);

        initToolbar();
        configureProgressDialogue();
        return rootView;
    }

    private void configureProgressDialogue() {
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setTitle("SMT Traders");
    }

    public void showProgressBar(boolean sync) {
        progressDoalog.setMessage(sync ? "Syncing..." : "Clearing Data...");
        progressDoalog.show();
    }

    public void hideProgressBar() {
        progressDoalog.dismiss();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        progressDoalog.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        progressDoalog.dismiss();
    }

    /**
     * Gets root fragment view
     *
     * @return
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * Calls onActivityResult inside fragment
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}

