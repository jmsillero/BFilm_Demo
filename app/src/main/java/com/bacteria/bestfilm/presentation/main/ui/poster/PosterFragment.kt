package com.bacteria.bestfilm.presentation.main.ui.poster

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bacteria.bestfilm.databinding.FragmentPosterBinding
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.FilmsGridAdapter
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.GridSpanCalculator

class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(PosterViewModel::class.java)

        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupUi()
        }

        return root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupUi() {
        binding.rvFilms.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = GridSpanCalculator()
            }
            adapter = FilmsGridAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}