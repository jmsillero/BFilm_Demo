package com.bacteria.bestfilm.presentation.main.ui.poster

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bacteria.bestfilm.databinding.FragmentPosterBinding
import com.bacteria.bestfilm.presentation.detail.FilmDetailActivity
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.FilmsGridAdapter
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.GridSpanCalculator
import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.Media
import com.bacteria.bestfilm.presentation.model.Route
import com.bacteria.bestfilm.presentation.model.Size
import java.time.Instant
import java.util.*

class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null
    private val binding get() = _binding!!

    private val posterViewModel: PosterViewModel by viewModels<PosterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUi()
        return root
    }


    private fun setupUi() {
        binding.rvFilms.apply {
            layoutManager = GridLayoutManager(context, 3).apply {
                spanSizeLookup = GridSpanCalculator()
            }

            adapter = FilmsGridAdapter(object : FilmsGridAdapter.OnItemFilmClickListener {
                override fun onItemClick(film: Film) {
                    val intent = Intent(activity, FilmDetailActivity::class.java)
                    startActivity(intent)
                }
            })


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}