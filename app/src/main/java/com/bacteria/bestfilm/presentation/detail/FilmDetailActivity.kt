package com.bacteria.bestfilm.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bacteria.bestfilm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FilmDetailFragment.newInstance())
                .commitNow()
        }
    }
}