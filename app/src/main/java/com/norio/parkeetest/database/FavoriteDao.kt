package com.norio.parkeetest.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Norio on 5/18/2023.
 */
@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * from favorite ORDER BY movieId ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>
}