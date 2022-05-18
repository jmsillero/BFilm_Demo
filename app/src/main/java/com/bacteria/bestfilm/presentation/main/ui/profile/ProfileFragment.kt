package com.bacteria.bestfilm.presentation.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.FragmentProfileBinding
import com.bacteria.bestfilm.presentation.BaseFragment
import com.bacteria.bestfilm.presentation.main.ui.poster.PosterViewModel
import com.bacteria.bestfilm.presentation.main.ui.poster.PosterViewModelState
import com.bacteria.bestfilm.presentation.main.ui.poster.adapter.FilmsGridAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModelState()
        return root
    }

    private fun setupViewModelState() {
        lifecycleScope.launch {
            profileViewModel.state.collect {
                when (it) {
                    is ProfileViewModelState.Success -> {
                        onSuccess(it)
                    }
                    is ProfileViewModelState.Error -> {
                        dismissProgressDialog()
                        showAlertDialog(it.error)
                    }
                    is ProfileViewModelState.IsLoading -> {
                        if (it.isLoading) {
                            showProgressDialog()
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun onSuccess(it: ProfileViewModelState.Success) {
        dismissProgressDialog()

        Glide.with(requireContext())
            .load(it.userEntity.profilePicture)
            .error(R.drawable.ic_person)
            .circleCrop()
            .into(binding.ivPicture)

        binding.lvName.value = "${it.userEntity.firstName} ${it.userEntity.lastName} "
        binding.lvEmail.value = it.userEntity.email
        binding.lvCard.value = it.userEntity.cardNumber
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}