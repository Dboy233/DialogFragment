package com.dboy.dialog.widget

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.dboy.dialog.R

/**
 * - 文件描述： 可能会引起泄露的dialog弹窗
 * @author Dboy
 * @since 2021/9/27 11:33
 */
class LoadingDialogFragment : DialogFragment(R.layout.dialog_loading_layout) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //dialog背景修改透明色
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }


}