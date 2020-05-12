package com.dboy.dialog.demo

import android.os.Bundle
import com.dboy.dialog.R
import com.dboy.dialog.kotlin.BaseDialogFragment
import kotlinx.android.synthetic.main.kotlin_dialog_demo_layout.*

class KotlinDialogFragmentDemo : BaseDialogFragment() {

    override fun getLayoutId(): Int = R.layout.kotlin_dialog_demo_layout

    override fun initViewAndData(savedInstanceState: Bundle?) {
        kotlin_demo_tv.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun getWidthPercentSize(): Float = 1f

    override fun getHeightPercentSize(): Float = 1f
}