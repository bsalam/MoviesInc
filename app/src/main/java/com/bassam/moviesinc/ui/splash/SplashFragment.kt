package com.bassam.moviesinc.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bassam.moviesinc.R
import com.bassam.moviesinc.utils.Extensions.enableFullScreen
import com.bassam.moviesinc.utils.consume


class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        enableFullScreen(true);

        viewModel.isReady().observe(viewLifecycleOwner, Observer {
            it.consume {
                findNavController().navigate(R.id.action_splashFragment_to_movieItemFragment)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        enableFullScreen(false)
    }
}