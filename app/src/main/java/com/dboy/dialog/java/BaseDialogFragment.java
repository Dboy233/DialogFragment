package com.dboy.dialog.java;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.dboy.dialog.MyDialog;
/**
 * @author  Dboy
 *   封装的basedialog
 */
public abstract class BaseDialogFragment extends DialogFragment implements IDialog {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        setDialogWidthSize(getWidthPercentSize(), getHeightPercentSize());
        initViewAndData(savedInstanceState);
    }

    @Override
    public <T extends View> T find(@IdRes int viewId) {
        return mRootView.findViewById(viewId);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new MyDialog(requireContext(), getTheme());
    }

    public void show(FragmentActivity fragmentActivity) {
        show(fragmentActivity.getSupportFragmentManager());
    }

    public void show(Fragment fragment) {
        show(fragment.getChildFragmentManager());
    }

    @Override
    public void show(FragmentManager manager) {
        try {
            manager.beginTransaction().remove(this).commitAllowingStateLoss();
            super.show(manager, getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
