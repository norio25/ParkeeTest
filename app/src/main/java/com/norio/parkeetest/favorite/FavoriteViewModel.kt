package com.norio.parkeetest.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.repository.FavoriteRepository

/**
 * Created by Norio on 5/18/2023.
 */
class FavoriteViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorite()
}