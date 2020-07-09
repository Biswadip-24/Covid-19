package com.app.learning.covid_19;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    private Activity mActivity;
    private AlertDialog mAlertDialog;
    LoadingDialog(Activity myActivity)
    {
        mActivity=myActivity;
    }
    void startLoadingDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);
        LayoutInflater inflater=mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.load,null));
        builder.setCancelable(false);

        mAlertDialog=builder.create();
        mAlertDialog.show();
    }
    void dismissDialog()
    {
        mAlertDialog.dismiss();
    }
}
