package com.bacteria.bestfilm.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.ActivityMainBinding
import com.bacteria.bestfilm.presentation.main.CoverActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

/**
 * Represent the activity that shows a form with
 * email input and password input
 * try to login user and navigate to the next screen
 * otherwise show an error message
 * */
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    /**
     * Setup UI actions
     * */
    private fun setupUi() {
        binding.btLogin.setOnClickListener { openMainScreen() }
    }


    private fun openMainScreen() {
        val coverActivityIntent = Intent(this, CoverActivity::class.java)
        startActivity(coverActivityIntent)
    }


}