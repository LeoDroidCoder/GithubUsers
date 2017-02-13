package com.leo.genesistask.view.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.leo.genesistask.R;


/**
 * Custom progress dialog with predefined theme
 *
 * Created by leonid on 2/11/17.
 */

public class LoadingProgressDialog extends ProgressDialog {

    public LoadingProgressDialog(Context context) {
        super(context, R.style.ProgressDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setup the progress dialog
        setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        setCancelable(false);
    }
}
