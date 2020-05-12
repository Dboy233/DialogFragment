package com.dboy.dialog.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dboy.dialog.R;
import com.dboy.dialog.java.BaseDialogFragment;

public class JavaDialogFragmentDemo extends BaseDialogFragment {
    @Override
    public int getLayoutId() {
        return R.layout.java_dialog_demo_layout;
    }

    @Override
    public void initViewAndData(Bundle savedInstanceState) {
        TextView view = find(R.id.java_demo_tv);
        view.setOnClickListener(new View.OnClickListener() {
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
