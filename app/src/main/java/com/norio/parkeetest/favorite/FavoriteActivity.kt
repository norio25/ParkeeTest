package com.norio.parkeetest.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.norio.parkeetest.R
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.databinding.ActivityFavoriteBinding
import com.norio.parkeetest.repository.ViewModelFactory

/**
 * Created by Norio on 5/18/2023.
 */
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        favoriteViewModel = obtainViewModel(this)
        getData()
    }

    private fun initToolbar() {
        binding.includeToolbarFavorite.toolbar.title = "Favorite"
        binding.includeToolbarFavorite.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.includeToolbarFavorite.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getData() {
        favoriteViewModel.getAllFavorite().observe(this) { favoriteList ->
            if (favoriteList != null) {
                val listFavorit = ArrayList<Favorite>()
                for (favorite in favoriteList) {
                    listFavorit.add(favorite)
                }
                val favoriteAdapter = FavoriteAdapter(listFavorit)
                binding.rvFavorite.layoutManager = LinearLayoutManager(this)
                binding.rvFavorite.adapter = favoriteAdapter
            } else {
                binding.rvFavorite.visibility = View.GONE
                binding.tvNoDataFavorite.visibility = View.VISIBLE
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }
}