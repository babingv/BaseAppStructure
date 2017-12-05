package com.oclocksoftware.appstructure.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.oclocksoftware.appstructure.R;
import com.oclocksoftware.appstructure.ui.fragment.BaseFragment;
import com.oclocksoftware.appstructure.utils.ResourcesHelper;

/**
 * Created by babin on 12/5/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static final int NO_TOOLBAR = 0;
    public static final int NEED_TOOLBAR = 1;
    public static final int ORDER_TOOLBAR = 2;
    private int toolbarState = NO_TOOLBAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, getContentView());
        onViewReady(savedInstanceState, getIntent());
        initToolbar();
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //To be used by child activities
        resolveDaggerDependency();
        setStatusBarcolor(getStatusbarcolor());
    }

    protected void resolveDaggerDependency() {
    }


    public void setToolbarState(int toolbarState) {
        this.toolbarState = toolbarState;
    }

    protected int getToolbarStyle() {
        return toolbarState;
    }

    protected void initToolbar() {
        switch (getToolbarStyle()) {
            case NEED_TOOLBAR:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ResourcesHelper
                        .getColor(this, R.color.colorAccent)));
                break;

            case NO_TOOLBAR:
            default:
                getSupportActionBar().hide();
        }
    }

    public void setToolbarVisibility(boolean visible) {
        Handler h = new Handler();
        if (visible) {
            h.post(() -> getSupportActionBar().show());
        } else {
            h.post(() -> getSupportActionBar().hide());
        }
    }


    public void setToolBarTitle(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    protected void setStatusBarcolor(int color) {
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, getStatusbarcolor()));
        }
    }

    protected abstract int getContentView();

    protected abstract int getStatusbarcolor();


    /**
     * Switches fragments in activity.
     *
     * @param fragment - next fragment
     */
    public void switchFragment(Fragment fragment, Bundle arg) {
        switchFragment(fragment, arg, false);
    }

    /**
     * Switches fragments in activity.
     *
     * @param fragment
     * @param fragmentTag
     */
    public void switchFragment(Fragment fragment, Bundle arg, String fragmentTag) {
        switchFragment(fragment, arg, false, fragmentTag);
    }

    /**
     * Switches fragments in activity.
     *
     * @param fragment
     * @param addToBackStack
     */
    public void switchFragment(Fragment fragment, Bundle arg, boolean addToBackStack) {
        switchFragment(true, arg, fragment, addToBackStack);
    }

    /**
     * Switches fragments in activity.
     *
     * @param fragment
     * @param addToBackStack
     * @param fragmentTag
     */
    public void switchFragment(Fragment fragment, Bundle arg, boolean addToBackStack, String fragmentTag) {
        switchFragment(true, arg, fragment, addToBackStack, fragmentTag);
    }

    /**
     * Switches fragments in activity.
     *
     * @param arg
     * @param replace
     * @param fragment
     * @param addToBackStack
     */
    public void switchFragment(boolean replace, Bundle arg, Fragment fragment, boolean addToBackStack) {
        switchFragment(replace, arg, fragment, addToBackStack, fragment.getClass().getSimpleName());
    }

    /**
     * Switches fragments in activity.
     *
     * @param replace
     * @param fragment
     * @param addToBackStack
     * @param fragmentTag
     */
    public void switchFragment(boolean replace, Bundle arg, Fragment fragment, boolean addToBackStack, String fragmentTag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (arg != null) {
            fragment.setArguments(arg);
        }
        transaction.replace(getContainerResId(), fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }

    public abstract int getContainerResId();


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();

            } else {
                onBackPressed();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
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

        if (findViewById(R.id.fragment_container) != null) {
            BaseFragment fragment = (BaseFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_container);
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        if (getmenuView() != 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(getmenuView(), menu);
            return true;
        } else {
            return true;
        }
    }


    protected abstract int getmenuView();

    public void goBack() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
