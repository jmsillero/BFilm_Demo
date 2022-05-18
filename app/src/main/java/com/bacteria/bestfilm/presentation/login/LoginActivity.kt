package com.bacteria.bestfilm.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.ActivityMainBinding
import com.bacteria.bestfilm.presentation.BaseActivity
import com.bacteria.bestfilm.presentation.detail.FilmDetailActivity
import com.bacteria.bestfilm.presentation.detail.FilmDetailViewModel
import com.bacteria.bestfilm.presentation.main.CoverActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint

/**
 * Represent the activity that shows a form with
 * email input and password input
 * try to login user and navigate to the next screen
 * otherwise show an error message
 * */
class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupViewModel()
    }


    /**
     * Setup UI actions
     * */
    private fun setupUi() {
        binding.btLogin.setOnClickListener { viewModel.performLogin() }

        binding.etUser.doOnTextChanged { text, start, before, count ->
            viewModel.email.value = text.toString()
        }
        binding.etPassword.doOnTextChanged { text, start, before, count ->
            viewModel.password.value = text.toString()
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is LoginViewModelState.Success -> {
                        dismissProgressDialog()
                        openMainScreen()
                    }
                    is LoginViewModelState.Error -> {
                        dismissProgressDialog()
                        showError(it)
                    }
                    is LoginViewModelState.IsLoading -> {
                        if (it.isLoading) {
                            showProgressDialog()
                        }

                    }
                    else -> Unit
                }

            }
        }
    }

    private fun showError(it: LoginViewModelState.Error) {
        var message = it.error
        if (message.isNullOrEmpty()) {
            message = getString(R.string.st_login_error_message)
        }
        showAlertDialog(message)
    }


    private fun openMainScreen() {
        val coverActivityIntent = Intent(this, CoverActivity::class.java)
        startActivity(coverActivityIntent)
    }


}