package com.bassam.moviesinc.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bassam.moviesinc.R
import com.bassam.moviesinc.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.login_fragment.*


/**
 * Created by Bassam Hamada on 7/7/20.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>() {

    companion object {
        private const val TAG = "redirected_url"
    }

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        render(savedInstanceState)
        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (!findNavController().popBackStack()) {
                requireActivity().finish()
            }
        }
    }

    private fun render(savedInstanceState: Bundle?) {

        viewModel.url.observe(viewLifecycleOwner, Observer {
            it?.let { loginWebView.loadUrl(it) }
        })

        viewModel.navigateNext.observe(viewLifecycleOwner, Observer {
            if (it) {

                findNavController().navigate(
                    R.id.movieItemListFragment,
                    savedInstanceState,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()
                )
            }
        })
    }

    private fun initWebView() {
        loginWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { viewModel.captureUrl(it) }
                return true
            }
        }
    }
}