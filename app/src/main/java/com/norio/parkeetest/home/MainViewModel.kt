package com.norio.parkeetest.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.norio.parkeetest.network.ApiConfig
import com.norio.parkeetest.response.NowPlayingMovieResponse
import com.norio.parkeetest.response.PopularMovieResponse
import com.norio.parkeetest.response.TopRatedMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Norio on 5/17/2023.
 */
class MainViewModel: ViewModel() {

    private val _popularMovie = MutableLiveData<PopularMovieResponse>()
    val popularMovie: LiveData<PopularMovieResponse> = _popularMovie

    private val _topRatedMovie = MutableLiveData<TopRatedMovieResponse>()
    val topRatedMovie: LiveData<TopRatedMovieResponse> = _topRatedMovie

    private val _nowPlayingMovie = MutableLiveData<NowPlayingMovieResponse>()
    val nowPlayingMovie: LiveData<NowPlayingMovieResponse> = _nowPlayingMovie

    init {
        getPopularMovie()
        getTopRatedMovie()
        getNowPlayingMovie()
    }

    private fun getPopularMovie() {
        val client = ApiConfig.getApiService().getPopularMovie("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWRkMjFmMjVlN2I3Y2M5ZDI1MTM0NmVmYzc0MjliMSIsInN1YiI6IjYyZDRlYTllNzJjMTNlMDA1OTA4M2Q4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xITvxx6f9dfQNXgPKqHfLQIHnwztIh2V1iDlTjsngrY")
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    _popularMovie.value = response.body()
                } else {
                    Log.e("Popular Movie", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e("Popular Movie", "onFailure: ${t.message.toString()}")
            }

        })
    }

    private fun getTopRatedMovie() {
        val client = ApiConfig.getApiService().getTopRated("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWRkMjFmMjVlN2I3Y2M5ZDI1MTM0NmVmYzc0MjliMSIsInN1YiI6IjYyZDRlYTllNzJjMTNlMDA1OTA4M2Q4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xITvxx6f9dfQNXgPKqHfLQIHnwztIh2V1iDlTjsngrY")
        client.enqueue(object : Callback<TopRatedMovieResponse> {
            override fun onResponse(
                call: Call<TopRatedMovieResponse>,
                response: Response<TopRatedMovieResponse>
            ) {
                if (response.isSuccessful) {
                    _topRatedMovie.value = response.body()
                } else {
                    Log.e("Top Rated Movie", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TopRatedMovieResponse>, t: Throwable) {
                Log.e("Top Rated Movie", "onFailure: ${t.message.toString()}")
            }

        })
    }

    private fun getNowPlayingMovie() {
        val client = ApiConfig.getApiService().getNowPlaying("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWRkMjFmMjVlN2I3Y2M5ZDI1MTM0NmVmYzc0MjliMSIsInN1YiI6IjYyZDRlYTllNzJjMTNlMDA1OTA4M2Q4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xITvxx6f9dfQNXgPKqHfLQIHnwztIh2V1iDlTjsngrY")
        client.enqueue(object : Callback<NowPlayingMovieResponse> {
            override fun onResponse(
                call: Call<NowPlayingMovieResponse>,
                response: Response<NowPlayingMovieResponse>
            ) {
                if (response.isSuccessful) {
                    _nowPlayingMovie.value = response.body()
                } else {
                    Log.e("Popular Movie", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NowPlayingMovieResponse>, t: Throwable) {
                Log.e("Popular Movie", "onFailure: ${t.message.toString()}")
            }

        })
    }
}