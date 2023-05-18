package com.norio.parkeetest.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.norio.parkeetest.network.ApiConfig
import com.norio.parkeetest.response.ReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Norio on 5/18/2023.
 */
class DetailViewModel : ViewModel() {

    private val _reviewMovie = MutableLiveData<ReviewResponse>()
    val reviewMovie: LiveData<ReviewResponse> = _reviewMovie

    fun getReviewMovie(movieId: Int?) {
        val client = ApiConfig.getApiService().getReviews(
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWRkMjFmMjVlN2I3Y2M5ZDI1MTM0NmVmYzc0MjliMSIsInN1YiI6IjYyZDRlYTllNzJjMTNlMDA1OTA4M2Q4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xITvxx6f9dfQNXgPKqHfLQIHnwztIh2V1iDlTjsngrY",
            movieId
        )
        client.enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if (response.isSuccessful) {
                    _reviewMovie.value = response.body()
                } else {
                    Log.e("Reviews Movie", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.e("Reviews Movie", "onFailure: ${t.message.toString()}")
            }

        })
    }
}