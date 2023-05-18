package com.norio.parkeetest.network

import com.norio.parkeetest.response.NowPlayingMovieResponse
import com.norio.parkeetest.response.PopularMovieResponse
import com.norio.parkeetest.response.ReviewResponse
import com.norio.parkeetest.response.TopRatedMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by Norio on 5/17/2023.
 */
interface ApiService {

    @GET("popular")
    fun getPopularMovie(
        @Header("Authorization") token: String,
    ): Call<PopularMovieResponse>

    @GET("top_rated")
    fun getTopRated(
        @Header("Authorization") token: String,
    ): Call<TopRatedMovieResponse>

    @GET("now_playing")
    fun getNowPlaying(
        @Header("Authorization") token: String,
    ): Call<NowPlayingMovieResponse>

    @GET("{movie_id}/reviews")
    fun getReviews(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int?
    ): Call<ReviewResponse>
}