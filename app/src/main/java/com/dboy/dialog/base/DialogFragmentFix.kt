package com.dboy.dialog.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.dboy.dialog.base.imp.OnCancelListenerImp
import com.dboy.dialog.base.imp.OnDismissListenerImp

/**
 * - 文件描述： 修复DialogFragment在某些特定情况下发生的内存泄露问题
 * 如果你的项目有封装的Dialog，直接继承这个就ok
 * @author Dboy
 * @since 2021/9/27 13:53
 */
open class DialogFragmentFix : DialogFragment {
    /**
     * 保证基础调用
     */
    constructor() : super()

    /**
     * 保证基础调用 1.3.0之前是没有这个构造器的
     */
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    /**
     * 记录是否反射替换成功
     */
    private var isLowVersion = false

    /**
     * 弱引用 取消Cancel回调接口 只在反射[replaceCallBackByReflexSuper]设置失败的时候进行初始化[replaceDialogCallBack]
     */
    private var mOnCancelListenerImp: OnCancelListenerImp? = null

    /**
     * 弱引用 关闭Dismiss回调接口 只在反射[replaceCallBackByReflexSuper]设置失败的时候进行初始化[replaceDialogCallBack]
     */
    private var mOnDismissListenerImp: OnDismissListenerImp? = null

    /**
     * 我们要在Dialog第一次使用这两个接口之前进行替换
     * 在这个方法执行的时候会创建dialog。
     * 如果[DialogFragment.onGetLayoutInflater]执行完成将会把这两个回调交给Dialog
     * 所以这里重写方法并在父类执行前进行反射操作。
     * 如果反射失败了，先让父类执行完成，然后通过修改Dialog的CallBack进行赋值。
     */
    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val isReplaceSuccess = replaceCallBackByReflexSuper()
        val layoutInflater = super.onGetLayoutInflater(savedInstanceState)
        if (!isReplaceSuccess) {
            Log.d("Dboy", "反射设置DialogFragment 失败！尝试设置Dialog监听")
            replaceDialogCallBack()
        } else {
            Log.d("Dboy", "反射设置DialogFragment 成功！")
        }
        return layoutInflater
    }

    /**
     * 如果是低版本中，即便是上面进行了一次替换操作，
     * 但是在低版本中super.onActivityCreated会对Dialog进行一次设置,所以这里需要进行覆盖
     */
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        if (isLowVersion) {
//            Log.d("Dboy", "低版本中重新替换覆盖")
//            replaceDialogCallBack()
//        }
//    }

    /**
     * 替换回调应用接口，减少内存泄露，不是没有内存泄露而是减少。
     */
    private fun replaceDialogCallBack() {
        if (mOnCancelListenerImp == null) {
            mOnCancelListenerImp = OnCancelListenerImp(this)
        }
        dialog?.setOnCancelListener(mOnCancelListenerImp)
        if (mOnDismissListenerImp == null) {
            mOnDismissListenerImp = OnDismissListenerImp(this)
        }
        dialog?.setOnDismissListener(mOnDismissListenerImp)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    /**
     * 通过反射替换父类的接口
     * @return true 反射替换接口成功
     */
    private fun replaceCallBackByReflexSuper(): Boolean {
        try {
            val superclass: Class<*> =
                findSuperclass(javaClass, DialogFragment::class.java) ?: return false
            //重新给取消接口赋值
            val mOnCancelListener = superclass.getDeclaredField("mOnCancelListener")
            mOnCancelListener.isAccessible = true
            mOnCancelListener.set(this, OnCancelListenerImp(this))
            //重新给关闭接口赋值
            val mOnDismissListener = superclass.getDeclaredField("mOnDismissListener")
            mOnDismissListener.isAccessible = true
            mOnDismissListener.set(this, OnDismissListenerImp(this))
            return true
        } catch (e: NoSuchFieldException) {
            Log.e("Dboy", "dialog 反射替换失败：未找到变量")
            Log.e("Dboy", "dialog 低版本")
            //找不到这个变量说明这个是低版本的
            isLowVersion = true
        } catch (e: IllegalAccessException) {
            Log.e("Dboy", "dialog 反射替换失败：不允许访问")
        }
        return false
    }

    /**
     * 查找对应[needFind]的超类 并返回class对象
     *
     * @param org      当前查找位置
     * @param needFind 需要找到的位置
     */
    private fun findSuperclass(org: Class<*>, needFind: Class<*>): Class<*>? {
        if (org.isAssignableFrom(needFind)) {
            return org
        }
        return if (org.superclass == null) {
            null
        } else findSuperclass(org.superclass!!, needFind)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //手动清理一下弱引用
        mOnCancelListenerImp?.clear()
        mOnCancelListenerImp = null

        mOnDismissListenerImp?.clear()
        mOnDismissListenerImp = null
    }
}