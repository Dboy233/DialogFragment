package com.dboy.dialog.java;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;

public interface IDialog {
    /**
     * 获取布局id
     */
    int getLayoutId();

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
     * 简化findViewById
     */
    <T extends View> T find(@IdRes int viewId);


    /**
     * 简化展示
     */
    void show(FragmentManager manager);
}
