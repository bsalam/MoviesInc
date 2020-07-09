package com.bassam.moviesinc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bassam.moviesinc.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bassam Hamada on 7/7/20.
 */
@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}