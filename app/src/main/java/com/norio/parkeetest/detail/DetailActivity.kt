package com.norio.parkeetest.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.norio.parkeetest.R
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.databinding.ActivityDetailBinding
import com.norio.parkeetest.repository.ViewModelFactory
import com.norio.parkeetest.response.ReviewResponse

/**
 * Created by Norio on 5/18/2023.
 */
class DetailActivity : AppCompatActivity() {

    private var listFavorite = ArrayList<Favorite>()
    private var statusFavorite = false
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var movieId: Int? = null
    private var favorite = Favorite()
    private lateinit var favoriteAddUpdateViewModel: FavoriteAddUpdateViewModel

    companion object {
        const val MOVIE_ID = "movie_id"
        const val IMAGE = "image"
        const val IMAGE_POSTER = "image_poster"
        const val TITLE = "title"
        const val DATE = "date"
        const val DESCRIPTION = "description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setDataReview()
        initButton()

        favoriteAddUpdateViewModel = obtainViewModel(this)
        getAllFavoriteList()
    }

    private fun initButton() {
        binding.btnBackDetail.setOnClickListener { finish() }
        binding.btnFav.setOnClickListener {
            setDatabase()
            statusFavorite = !statusFavorite
            setStatusFavorite()
        }
        binding.btnShare.setOnClickListener {
            val openSare = ShareFragment()
            openSare.show(supportFragmentManager, openSare.tag)
        }
    }

    private fun getData() {
        if (intent.extras != null) {
            movieId = intent.getIntExtra(MOVIE_ID, 0)
            val image = intent.getStringExtra(IMAGE)
            val title = intent.getStringExtra(TITLE)
            val date = intent.getStringExtra(DATE)
            val desc = intent.getStringExtra(DESCRIPTION)
            val imagePoster = intent.getStringExtra(IMAGE_POSTER)
            viewModel.getReviewMovie(movieId)

            favorite = Favorite(movieId, title, date, imagePoster, desc)
            setData(image, title, date, desc)
        }
    }

    private fun setData(image: String?, title: String?, date: String?, desc: String?) {
        Glide.with(this).load(image).into(binding.ivDetail)

        binding.tvTitleDetail.text = title
        binding.tvDateDetail.text = date
        binding.tvDescDetail.text = desc
    }

    private fun setDataReview() {
        viewModel.reviewMovie.observe(this) { reviews ->
            if (!reviews.results.isNullOrEmpty()) {
                val listReview = ArrayList<ReviewResponse.Result>()
                for (dataReview in reviews.results) {
                    dataReview?.let { listReview.add(it) }
                }
                val reviewAdapter = ReviewAdapter(listReview)
                binding.rvReviewDetail.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvReviewDetail.adapter = reviewAdapter
            }
        }
    }

    private fun getAllFavoriteList() {
        favoriteAddUpdateViewModel.getAllFavorite().observe(this) { favoriteList ->
            if (favoriteList != null) {
                for (favorite in favoriteList) {
                    listFavorite.add(favorite)
                    if (favorite.movieId == movieId) statusFavorite = true
                }
                setStatusFavorite()
            } else setStatusFavorite()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteAddUpdateViewModel::class.java]
    }

    private fun setStatusFavorite() {
        if (statusFavorite) binding.btnFav.setImageResource(R.drawable.ic_favorite_black)
        else binding.btnFav.setImageResource(R.drawable.ic_favorite_border)
    }

    private fun setDatabase() {
        if (statusFavorite) favoriteAddUpdateViewModel.delete(favorite)
        else favoriteAddUpdateViewModel.insert(favorite)
    }
}