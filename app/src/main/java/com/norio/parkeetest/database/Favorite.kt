package com.norio.parkeetest.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Norio on 5/18/2023.
 */
@Entity
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    var movieId: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
) : Parcelable