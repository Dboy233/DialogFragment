package com.dboy.dialog.kotlin.viewBinding

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.FloatRange
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.dboy.dialog.MyDialog
/**
 * @author  Dboy
 *  viewBinding封装的baseDialog
 */
open abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment(), IDialog<T> {

    lateinit var mRootView: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = getRootViewBinding(inflater, container)
        return mRootView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogWidthSize(getWidthPercentSize(), getHeightPercentSize())
        initViewAndData(savedInstanceState)
    }

    open fun show(activity: FragmentActivity) {
        show(activity.supportFragmentManager)
    }

    open fun show(fragment: Fragment) {
        show(fragment.childFragmentManager)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog(requireContext(),theme)
    }
    override fun show(manager: FragmentManager) {
        try {
            manager.beginTransaction().remove(this).commitAllowingStateLoss()
            super.show(manager, javaClass::getSimpleName.toString())
        } catch (e: Exception) {
        }
    }

    protected fun setDialogWidthSize(widthPercent: Float, heightPercent: Float) {
        val dm = DisplayMetrics()
        if (activity != null && dialog != null) {
            activity?.windowManager?.defaultDisplay?.getMetrics(dm)
            val window = dialog?.window
            if (window != null) {
                window.decorView.setPadding(0, 0, 0, 0)
                val lp = window.attributes
                lp.width =
                    if (widthPercent == 0f) WindowManager.LayoutParams.WRAP_CONTENT else if (widthPercent == 1f) WindowManager.LayoutParams.MATCH_PARENT else (dm.widthPixels * widthPercent).toInt()
                lp.height =
                    if (heightPercent == 0f) WindowManager.LayoutParams.WRAP_CONTENT else if (heightPercent == 1f) WindowManager.LayoutParams.MATCH_PARENT else (dm.heightPixels * heightPercent).toInt()
                window.attributes = lp
                window.setBackgroundDrawable(ColorDrawable())
            }
        }
    }

}

interface IDialog<T : ViewBinding> {

    /**
     * 获取binding
     */
    fun getRootViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    /**
     * 初始化视图和数据
     */
    fun initViewAndData(savedInstanceState: Bundle?)

    /**
     * @return dialog的宽度比例 0=内容包裹，1=匹配父布局， 0.0~1.0 按屏幕比例计算
     */
    @FloatRange(from = 0.0, to = 1.0)
    fun getWidthPercentSize(): Float

    /**
     * @return dialog的高度比例 0=内容包裹， 1=匹配父布局， 0.0~1.0 按屏幕比例计算
     */
    @FloatRange(from = 0.0, to = 1.0)
    fun getHeightPercentSize(): Float

    /**
     * 简化展示
     */
    fun show(manager: FragmentManager)
}