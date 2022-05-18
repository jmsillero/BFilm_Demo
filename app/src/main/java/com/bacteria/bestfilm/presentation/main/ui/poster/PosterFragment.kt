package com.bacteria.bestfilm.presentation.main.ui.poster

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bacteria.bestfilm.databinding.FragmentPosterBinding
import com.bacteria.bestfilm.presentation.BaseFragment
import com.bacteria.bestfilm.presentation.detail.FilmDetailActivity
import com.bacteria.bestfilm.presentation.login.LoginViewModelState
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.FilmsGridAdapter
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.GridSpanCalculator
import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.Media
import com.bacteria.bestfilm.presentation.model.Route
import com.bacteria.bestfilm.presentation.model.Size
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

@AndroidEntryPoint
class PosterFragment : BaseFragment() {

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
        setupViewModelState()
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
                    intent.putExtra("film_id", film.id)
                    startActivity(intent)
                }
            })
        }
    }

    private fun setupViewModelState() {
        lifecycleScope.launch {
            posterViewModel.state.collect {
                when (it) {
                    is PosterViewModelState.Success -> {
                        dismissProgressDialog()
                        (binding.rvFilms.adapter as FilmsGridAdapter).updateData(
                            it.result,
                            it.posterRoute
                        )
                    }
                    is PosterViewModelState.Error -> {
                        dismissProgressDialog()
                        showAlertDialog(it.error)
                    }
                    is PosterViewModelState.IsLoading -> {
                        if (it.isLoading) {
                            showProgressDialog()
                        }

                    }
                    else -> Unit
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}