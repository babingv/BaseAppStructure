package com.oclocksoftware.appstructure.view;

/**
 * Created by babin on 12/5/2017.
 */

public interface BaseView {
    /**
     * UI action to display progress of sing in process.
     * Shows progress bar.
     */
    void onStartAction();

    /**
     * UI action that end to display progress of sing in process.
     * Hides progress bar.
     */
    void onFinishAction();

    /**
     * Hides error dialog.
     */
    void hideError();

    void showSnackError(int resId);
    void showSnackError(String text);
}
