package com.dboy.dialog.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dboy.dialog.databinding.JavaDialogDemoLayoutBinding;
import com.dboy.dialog.java.viewBinding.BaseDialogFragment;


public class JavaViewBindingDialogDemo extends BaseDialogFragment<JavaDialogDemoLayoutBinding> {

    @Override
    public JavaDialogDemoLayoutBinding getRootViewBinding(LayoutInflater inflater, ViewGroup container) {
        return JavaDialogDemoLayoutBinding.inflate(inflater, container, false);
    }

    @Override
    public void initViewAndData(Bundle savedInstanceState) {
        mRootView.javaDemoTv.setText("Java ViewBinding Dialog");
        mRootView.javaDemoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public float getWidthPercentSize() {
        return 1;
    }

    @Override
    public float getHeightPercentSize() {
        return 1;
    }
}
