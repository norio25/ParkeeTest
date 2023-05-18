package com.norio.parkeetest.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.repository.FavoriteRepository

/**
 * Created by Norio on 5/18/2023.
 */
class FavoriteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorite()

    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun update(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }
}