package com.dboy.dialog.base.imp

import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import com.dboy.dialog.base.iner.IDialogFragmentReferenceClear
import java.lang.ref.WeakReference

/**
 * 弱引用调用取消监听
 * 还是有几率导致内存泄露，
 * 待系统主动GC时释放资源
 */
class OnCancelListenerImp(dialogFragment: DialogFragment) :
    DialogInterface.OnCancelListener, IDialogFragmentReferenceClear {

    override val fragmentWeakReference: WeakReference<DialogFragment> =
        WeakReference(dialogFragment)

    override fun onCancel(dialog: DialogInterface) {
        fragmentWeakReference.get()?.onCancel(dialog)
    }

    override fun clear() {
        fragmentWeakReference.clear()
    }
}