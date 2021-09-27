package com.dboy.dialog.widget

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dboy.dialog.R
import com.dboy.dialog.base.DialogFragmentFix

/**
 * - 文件描述： 修复内存泄露的Dialog弹窗
 *
 * @author Dboy
 * @since 2021/9/27 14:21
 */
class FixLoadingDialogFragment : DialogFragmentFix(R.layout.dialog_loading_layout) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //dialog背景修改透明色
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}