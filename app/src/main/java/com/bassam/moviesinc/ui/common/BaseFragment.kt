package com.bassam.moviesinc.ui.common

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


/**
 * Created by Bassam Hamada on 7/10/20.
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    protected abstract val viewModel: T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.msgRes, Toast.LENGTH_LONG).show()
        })
    }

    open fun showLoading(show: Boolean) {

    }
}