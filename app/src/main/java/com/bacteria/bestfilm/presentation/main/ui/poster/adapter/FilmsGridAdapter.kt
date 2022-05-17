package com.bacteria.bestfilm.presentation.main.ui.poster.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacteria.bestfilm.presentation.custom.FilmItemView
import com.bacteria.bestfilm.presentation.model.Film

class FilmsGridAdapter(private val onItemFilmClickListener: OnItemFilmClickListener) :
    RecyclerView.Adapter<FilmsGridAdapter.ItemFilmViewHolder>() {
    private val films: MutableList<Film> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmViewHolder {
        return ItemFilmViewHolder(FilmItemView(parent.context), onItemFilmClickListener)
    }

    override fun onBindViewHolder(holder: ItemFilmViewHolder, position: Int) {
        val film = films[position]

        holder.setData(film)
    }

    override fun getItemCount() = films.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<Film>) {
        films.clear()
        films.addAll(list)
        notifyDataSetChanged()
    }

    class ItemFilmViewHolder(
        private val itemView: FilmItemView,
        private val onItemFilmClickListener: OnItemFilmClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(film: Film) {
            (itemView as FilmItemView).label = film.name
            val posterImage = film.getMediaByCode("poster")
            itemView.image = posterImage
            itemView.setOnClickListener { onItemFilmClickListener.onItemClick(film) }
        }
    }

    interface OnItemFilmClickListener {
        fun onItemClick(film: Film)
    }

}