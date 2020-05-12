package com.dboy.dialog.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dboy.dialog.databinding.KotlinDialogDemoLayoutBinding

import com.dboy.dialog.kotlin.viewBinding.BaseDialogFragment

class KotlinViewBindingDialogDemo : BaseDialogFragment<KotlinDialogDemoLayoutBinding>() {

    override fun getRootViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): KotlinDialogDemoLayoutBinding {
        return KotlinDialogDemoLayoutBinding.inflate(inflater, container, false)
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        mRootView.kotlinDemoTv.text = "显示 kotlin ViewBinding BaseDialogFragmentDialog Demo"
        mRootView.kotlinDemoTv.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun getWidthPercentSize(): Float = 1f

    override fun getHeightPercentSize(): Float = 1f


}