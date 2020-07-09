package com.bassam.moviesinc.utils

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Bassam Hamada on 7/7/20.
 */
object Extensions {

    fun Fragment.enableFullScreen(enable: Boolean) {

        if (enable) {
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        } else {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        }
    }
}