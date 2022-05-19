package com.bacteria.bestfilm.presentation.detail


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
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer

@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private var filmId: Long = 0

    private val viewModel: FilmDetailViewModel by viewModels<FilmDetailViewModel>()

    private var mLibVLC: LibVLC? = null
    private var mMediaPlayer: MediaPlayer? = null

    companion object {
        val EXTRA_ID = "EXTRA_ID"
        fun newInstance(id: Long): FilmDetailFragment {
            val extras = Bundle()
            extras.putLong(EXTRA_ID, id)
            val fragment = FilmDetailFragment()
            fragment.arguments = extras
            return fragment
        }

        private const val USE_TEXTURE_VIEW = false
        private const val ENABLE_SUBTITLES = true
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPlayerView()
        setupViewModel()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initPlayerView() {

        mLibVLC = LibVLC(requireActivity(), ArrayList<String>().apply {
            add("--no-drop-late-frames")
            add("--no-skip-frames")
            add("--vout=android-display");
            add("--rtsp-tcp")
            add("-vvv")
        })
        mMediaPlayer = MediaPlayer(mLibVLC)
        val vout = mMediaPlayer?.vlcVout
        vout?.setWindowSize(binding.vvPreview.width, binding.vvPreview.height)
        mMediaPlayer?.attachViews(binding.vvPreview, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW)

    }


    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is DetailViewModelState.Success -> {
                        val video = it.result.media?.find { it.code == "trailer_mp4" }?.resource
                        try {
                            Media(mLibVLC, Uri.parse("${it.route.sizes!!.medium}$video")).apply {
                                setHWDecoderEnabled(true, false);
                                addOption(":network-caching=150");
                                addOption(":clock-jitter=0");
                                addOption(":clock-synchro=0");

                                mMediaPlayer?.media = this
                                this.setHWDecoderEnabled(true, true)

                            }.release()

                            mMediaPlayer?.play()
                        } catch (e: Exception) {
                        }
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


    override fun onStop() {
        super.onStop()
        mMediaPlayer?.stop()
        mMediaPlayer?.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.release()
        mLibVLC?.release()
    }


}