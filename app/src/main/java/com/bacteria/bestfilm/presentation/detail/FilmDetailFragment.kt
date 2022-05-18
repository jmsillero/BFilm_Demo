package com.bacteria.bestfilm.presentation.detail

import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.FragmentFilmDetailBinding
import com.bacteria.bestfilm.presentation.main.ui.poster.PosterViewModelState
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.FilmsGridAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private var filmId: Long = 0

    private val viewModel: FilmDetailViewModel by viewModels<FilmDetailViewModel>()

    companion object {
        val EXTRA_ID = "EXTRA_ID"
        fun newInstance(id: Long): FilmDetailFragment {
            val extras = Bundle()
            extras.putLong(EXTRA_ID, id)
            val fragment = FilmDetailFragment()
            fragment.arguments = extras
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filmId = requireArguments().getLong(EXTRA_ID, 0)
        viewModel.loadFilm(filmId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(inflater, container, false)

        setupUi()
        setupViewModel()
        return binding.root
    }

    private fun setupUi() {
        val mediaController = MediaController(context)
        mediaController.setAnchorView(binding.vvPreview)
        mediaController.setMediaPlayer(binding.vvPreview)

        binding.vvPreview.setMediaController(mediaController)
        binding.vvPreview.setOnPreparedListener {
            MediaPlayer.OnPreparedListener {
                binding.vvPreview.seekTo(1)
                binding.vvPreview.start()
            }
        }


    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is DetailViewModelState.Success -> {
                        val video = it.result.media?.find { it.code == "trailer_mp4" }
                        binding.vvPreview.setVideoURI(Uri.parse("${it.route.sizes!!.medium}$video"))
                        binding.vvPreview.requestFocus()

                        binding.lvName.value = it.result.name
                        binding.lvClasification.value = it.result.categories?.get(0)
                        binding.lvGenre.value = it.result.genre
                        binding.lvDuration.value = it.result.length
                        binding.lvSynopsis.value = it.result.synopsis
                    }
                    is DetailViewModelState.Error -> {

                    }
                    is DetailViewModelState.IsLoading -> {

                    }
                    else -> Unit
                }

            }
        }
    }


}