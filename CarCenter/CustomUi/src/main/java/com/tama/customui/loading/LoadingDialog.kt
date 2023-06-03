package com.tama.customui.loading

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Display
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.tama.customui.R

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_loading)
        val window: Window? = window
        val size = Point()
        val display: Display? = window?.windowManager?.defaultDisplay
        display?.getSize(size)
        val width = (context.resources.displayMetrics.widthPixels * 1.0).toInt()
        val height = (context.resources.displayMetrics.heightPixels * 1.0).toInt()
        window?.setLayout(width, height)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.CENTER)
        val wlp: WindowManager.LayoutParams? = window?.attributes
        wlp?.gravity = Gravity.CENTER
        wlp?.flags = wlp?.flags?.and(WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv())
        window?.attributes = wlp
        setCancelable(false)
    }

    companion object {
        fun create(context: Context): LoadingDialog {
            return LoadingDialog(context)
        }
    }

    fun showDialog() {
        if (!isShowing) {
            show()
        }
    }

    fun dism() {
        if (isShowing) {
            dismiss()
        }
    }
}