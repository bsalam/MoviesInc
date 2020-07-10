package com.bassam.moviesinc.ui.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bassam.moviesinc.R

import com.bassam.moviesinc.ui.list.dummy.DummyContent.DummyItem

/**
 * Created by Bassam Hamada on 7/7/20.
 *
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieItemRecyclerViewAdapter(
    private val values: List<DummyItem>
) : RecyclerView.Adapter<MyMovieItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = values[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val idView: TextView = view.findViewById(R.id.item_number)
//        val contentView: TextView = view.findViewById(R.id.content)
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }
}