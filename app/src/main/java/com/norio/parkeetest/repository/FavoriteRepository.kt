package com.norio.parkeetest.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.database.FavoriteDao
import com.norio.parkeetest.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Norio on 5/18/2023.
 */
class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<Favorite>> = mFavoriteDao.getAllFavorites()

    fun insert(favorite: Favorite) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }

    fun delete(favorite: Favorite) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }

    fun update(favorite: Favorite) {
        executorService.execute { mFavoriteDao.update(favorite) }
    }
}