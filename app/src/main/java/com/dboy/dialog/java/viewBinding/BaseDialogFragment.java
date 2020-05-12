package com.dboy.dialog.java.viewBinding;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;


public abstract class BaseDialogFragment<T extends ViewBinding> extends DialogFragment implements IDialog<T> {

    protected T mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = getRootViewBinding(inflater, container);
        return mRootView.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDialogWidthSize(getWidthPercentSize(), getHeightPercentSize());
        initViewAndData(savedInstanceState);
    }

    private void setDialogWidthSize(float widthPercent, float heightPercent) {
        DisplayMetrics dm = new DisplayMetrics();
        FragmentActivity activity = getActivity();
        Dialog dialog = getDialog();
        if (activity != null && dialog != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            Window window = dialog.getWindow();
            if (window != null) {
                window.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams lp = window.getAttributes();

                lp.width = widthPercent == 0 ? WindowManager.LayoutParams.WRAP_CONTENT : widthPercent == 1 ? WindowManager.LayoutParams.MATCH_PARENT : (int) (dm.widthPixels * widthPercent);
                lp.height = heightPercent == 0 ? WindowManager.LayoutParams.WRAP_CONTENT : heightPercent == 1 ? WindowManager.LayoutParams.MATCH_PARENT : (int) (dm.heightPixels * heightPercent);

                window.setAttributes(lp);
                window.setBackgroundDrawable(new ColorDrawable());
            }
        }
    }

    public void show(@NonNull FragmentManager manager) {
        super.show(manager, getClass().getSimpleName());
    }
}
