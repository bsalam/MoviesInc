package com.bassam.moviesinc.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassam.moviesinc.R
import com.bassam.moviesinc.api.data.Common
import com.bassam.moviesinc.api.data.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieListRecyclerViewAdapter(
    private val values: List<Result>,
    private val onItemClicked: ((movieId: Int) -> Unit)?,
    private val onMarkFavClicked: ((fav: Boolean, movieId: Int) -> Unit)?,
    private val showAddFav: Boolean,
    private val showRemoveFav: Boolean

) : RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {
    constructor(values: List<Result>) : this(
        values,
        null,
        null,
        false,
        false
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Result) {

            Glide.with(view.context)
                .load(Common.IMAGE_URL_W154 + item.posterPath)
                .into(view.poster)
            view.title.text = item.title
            view.rate.text = item.voteAverage.toString()
            view.release.text = item.releaseDate
            when (showAddFav) {
                true -> {
                    view.addFavBtn.visibility = View.VISIBLE
                }
                false -> {
                    view.addFavBtn.visibility = View.GONE
                }
            }
            when (showRemoveFav) {
                true -> {
                    view.removeFavBtn.visibility = View.VISIBLE
                }
                false -> {
                    view.removeFavBtn.visibility = View.GONE
                }
            }

            view.addFavBtn.setOnClickListener() {
                onMarkFavClicked?.let { it1 -> it1(true, item.id) }
            }

            view.removeFavBtn.setOnClickListener() {
                onMarkFavClicked?.let { it1 -> it1(false, item.id) }
            }

            view.list_item.setOnClickListener {
                onItemClicked?.let { it1 -> it1(item.id) }
            }
        }
    }
}