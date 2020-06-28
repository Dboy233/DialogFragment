package com.dboy.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author 夜斗
 * @date 2020/6/28
 * Class 描述 :
 */
public class MyDialog  extends Dialog {
    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {

    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {

    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {

    }
}
