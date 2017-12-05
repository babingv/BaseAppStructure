package com.oclocksoftware.appstructure.presenter;

/**
 * Created by babin on 12/5/2017.
 */

public interface Presenter {
    void onCreate();

    void onPause();

    void onResume();

    void onDestroy();
}
