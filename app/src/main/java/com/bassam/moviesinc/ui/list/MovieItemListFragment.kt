package com.bassam.moviesinc.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bassam.moviesinc.R
import com.bassam.moviesinc.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_list_fragment.*

/**
 * Created by Bassam Hamada on 7/7/20.
 *
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class MovieItemListFragment : BaseFragment<MovieItemListViewModel>() {

    override val viewModel: MovieItemListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.isFav = it.getBoolean(getString(R.string.bundle_is_fav_key))
        }
        viewModel.load()

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getResults().observe(viewLifecycleOwner, Observer {
            recycler_view.adapter = MovieListRecyclerViewAdapter(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            val bundle = bundleOf(getString(R.string.bundle_is_fav_key) to true)
            findNavController().navigate(
                R.id.movieItemListFragment,
                bundle
            )
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_favorite).isVisible = !viewModel.isFav
    }
}