package com.dboy.dialog.java.viewBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.FloatRange;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

public interface IDialog<T extends ViewBinding> {

    /**
     * 获取view布局Binding
     */
    T getRootViewBinding(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化视图和数据
     */
    void initViewAndData(Bundle savedInstanceState);

    /**
     * @return dialog的宽度比例 0=内容包裹，1=匹配父布局， 0.0~1.0 按屏幕比例计算
     */
    @FloatRange(from = 0.0, to = 1.0)
    float getWidthPercentSize();

    /**
     * @return dialog的高度比例 0=内容包裹， 1=匹配父布局， 0.0~1.0 按屏幕比例计算
     */
    @FloatRange(from = 0.0, to = 1.0)
    float getHeightPercentSize();

    /**
     * 简化展示
     */
    void show(FragmentManager manager);
}
