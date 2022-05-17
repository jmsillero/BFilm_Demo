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
import androidx.fragment.app.viewModels
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.FragmentFilmDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilmDetailViewModel by viewModels<FilmDetailViewModel>()

    companion object {
        fun newInstance() = FilmDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(inflater, container, false)

        setupUi()
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

        //TODO: pass url only for testing
        binding.vvPreview.setVideoURI(Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"))
        binding.vvPreview.requestFocus()

    }


}