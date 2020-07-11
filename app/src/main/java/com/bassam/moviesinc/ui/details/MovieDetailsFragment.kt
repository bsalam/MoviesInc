package com.bassam.moviesinc.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bassam.moviesinc.R
import com.bassam.moviesinc.api.data.Common
import com.bassam.moviesinc.api.data.DetailsRes
import com.bassam.moviesinc.ui.common.BaseFragment
import com.bassam.moviesinc.ui.list.MovieListRecyclerViewAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.rate_dlg.view.*


/**
 * Created by Bassam Hamada on 7/7/20.
 */
@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {

    override val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.movieId = it.getInt(getString(R.string.bundle_movie_id_key))
        }
        viewModel.load()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFavBtn.setOnClickListener {
            viewModel.markFav()
        }

        rateBtn.setOnClickListener {
            showRateDialog()
        }
    }

    private fun showRateDialog() {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.rate_dlg, null)
        context?.let {
            val mBuilder = AlertDialog.Builder(it)
                .setView(mDialogView)
                .setTitle(R.string.rate_title)
            val mAlertDialog = mBuilder.show()
            mDialogView.submitBtn.setOnClickListener {
                mAlertDialog.dismiss()
                val rating = mDialogView.rateBar.rating
                viewModel.rate(rating.toDouble())
            }
            mDialogView.cancelBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getResult().observe(viewLifecycleOwner, Observer {
            bind(it)
        })

        viewModel.getCast().observe(viewLifecycleOwner, Observer {
            castResult.text = it
        })

        viewModel.getRecommendationsResult().observe(viewLifecycleOwner, Observer {
            recommendationsRecyclerView.adapter =
                MovieListRecyclerViewAdapter(it)
        })

        viewModel.isAddedToFav().observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(activity, R.string.added_to_fav, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isRated().observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(activity, R.string.rate_done, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bind(result: DetailsRes?) {
        result?.let {
            Glide.with(this)
                .load(Common.IMAGE_URL_W500 + it.posterPath)
                .into(poster)
            title.text = it.title
            release.text = it.releaseDate
            rate.text = it.voteAverage.toString()
            genre.text = it.genres[1].name // TODO enhance later to show all
            overview.text = result.overview
        }
    }

}