package com.bacteria.bestfilm.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.bacteria.bestfilm.R
import com.bacteria.bestfilm.databinding.ActivityFilmDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val id = intent.getLongExtra("film_id", 0)
        openMainFragment(savedInstanceState, id)
    }

    private fun openMainFragment(savedInstanceState: Bundle?, filmId: Long) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FilmDetailFragment.newInstance(filmId))
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.film_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_share -> {
                shareFilm()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareFilm() {

    }
}