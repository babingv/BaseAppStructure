package com.oclocksoftware.appstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oclocksoftware.appstructure.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_frame_container;
    }

    @Override
    protected int getStatusbarcolor() {
        return R.color.colorAccent;
    }

    @Override
    public int getContainerResId() {
        return R.id.fragment_container;
    }

    @Override
    protected int getmenuView() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TO FINISH ALL BACK STACKS
        finishAffinity();
    }
}
