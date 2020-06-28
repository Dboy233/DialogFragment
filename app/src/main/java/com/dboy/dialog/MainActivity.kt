package com.dboy.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dboy.dialog.demo.JavaDialogFragmentDemo
import com.dboy.dialog.demo.JavaViewBindingDialogDemo
import com.dboy.dialog.demo.KotlinDialogFragmentDemo
import com.dboy.dialog.demo.KotlinViewBindingDialogDemo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showKotlinDemo(view: View) {
        val dialog = KotlinDialogFragmentDemo()
        dialog.show(this)
    }

    fun showViewBindingKotlinDemo(view: View) {
        val dialog = KotlinViewBindingDialogDemo()
        dialog.show(this)
    }

    fun showJavaDemo(view: View) {
        val dialog = JavaDialogFragmentDemo()
        for (i in 0..1) {
            dialog.show(this)
        }
    }

    fun showViewBindingJavaDemo(view: View) {
        val dialog = JavaViewBindingDialogDemo()
        dialog.show(this)
    }
}
