package com.bassam.moviesinc.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassam.moviesinc.R
import com.bassam.moviesinc.api.data.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieListRecyclerViewAdapter(
    private val values: List<Result>
) : RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w154"
    }

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

        init {
            // init listeners
        }

        fun bind(item: Result) {

            Glide.with(view.context)
                .load(IMAGE_URL + item.posterPath)
                .into(view.poster)
            view.title.text = item.title
            view.rate.text = item.voteAverage.toString()
            view.release.text = item.releaseDate
        }
    }
}