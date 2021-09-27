package com.dboy.dialog.base.imp

import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import com.dboy.dialog.base.iner.IDialogFragmentReferenceClear
import java.lang.ref.WeakReference

/**
 * 弱引用调用关闭监听
 * 还是有几率导致内存泄露，
 * 待系统主动GC时释放资源
 */
class OnDismissListenerImp(dialogFragment: DialogFragment) :
    DialogInterface.OnDismissListener, IDialogFragmentReferenceClear {

    override val fragmentWeakReference: WeakReference<DialogFragment> =
        WeakReference(dialogFragment)

    override fun onDismiss(dialog: DialogInterface) {
        fragmentWeakReference.get()?.onDismiss(dialog)
    }

    override fun clear() {
        fragmentWeakReference.clear()
    }
}