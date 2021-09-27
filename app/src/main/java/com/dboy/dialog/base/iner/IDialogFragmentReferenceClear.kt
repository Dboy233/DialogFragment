package com.dboy.dialog.base.iner

import androidx.fragment.app.DialogFragment
import java.lang.ref.WeakReference

/**
 * - 文件描述： 清理弱引用
 * @author Dboy
 * @since 2021/9/27 13:56
 */
interface IDialogFragmentReferenceClear {

    val fragmentWeakReference: WeakReference<DialogFragment>

    fun clear()
}