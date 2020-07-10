package com.bassam.moviesinc.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bassam.moviesinc.R
import com.bassam.moviesinc.utils.Extensions.enableFullScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bassam Hamada on 7/7/20.
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        enableFullScreen(true)

        viewModel.isShowLogin().observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(
                    R.id.loginFragment,
                    savedInstanceState,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment, true)
                        .build()
                )
            }
        })

        viewModel.isShowMovieList().observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().navigate(
                    R.id.movieItemListFragment,
                    savedInstanceState,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment, true)
                        .build()
                )
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        enableFullScreen(false)
    }
}