package com.dboy.dialog

import androidx.appcompat.app.AppCompatActivity
import com.dboy.dialog.widget.FixLoadingDialogFragment
import com.dboy.dialog.widget.LoadingDialogFragment
/**
 * - 文件描述：Demo
 * @author Dboy
 * @since 2021/9/27
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun showLoading(view: android.view.View) {
        LoadingDialogFragment().show(supportFragmentManager, "LoadingDialogFragment")
    }

    fun showFixLoading(view: android.view.View) {
        FixLoadingDialogFragment().show(supportFragmentManager,"FixLoadingDialogFragment")
    }

}
