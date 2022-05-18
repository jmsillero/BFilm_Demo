package com.bacteria.bestfilm.presentation.main.ui.poster.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacteria.bestfilm.presentation.custom.FilmItemView
import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.Route

class FilmsGridAdapter(private val onItemFilmClickListener: OnItemFilmClickListener) :
    RecyclerView.Adapter<FilmsGridAdapter.ItemFilmViewHolder>() {
    private val films: MutableList<Film> = mutableListOf()
    private var route: Route? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmViewHolder {
        return ItemFilmViewHolder(FilmItemView(parent.context), onItemFilmClickListener)
    }

    override fun onBindViewHolder(holder: ItemFilmViewHolder, position: Int) {
        val film = films[position]
        holder.setData(film, route)
    }

    override fun getItemCount() = films.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<Film>, route: Route?) {
        films.clear()
        films.addAll(list)
        this.route = route
        notifyDataSetChanged()
    }

    class ItemFilmViewHolder(
        private val itemView: FilmItemView,
        private val onItemFilmClickListener: OnItemFilmClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(film: Film, route: Route?) {
            (itemView as FilmItemView).label = film.name
            val path = route?.sizes?.medium
            val file = film.media?.find { it -> it.code == "poster" }?.resource

            if (path != null && file != null) {
                itemView.image = "$path$file"
            }

            itemView.setOnClickListener { onItemFilmClickListener.onItemClick(film) }
        }
    }

    interface OnItemFilmClickListener {
        fun onItemClick(film: Film)
    }

}