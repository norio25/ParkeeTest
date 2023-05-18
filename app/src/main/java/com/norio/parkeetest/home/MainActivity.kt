package com.norio.parkeetest.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.norio.parkeetest.R
import com.norio.parkeetest.databinding.ActivityMainBinding
import com.norio.parkeetest.detail.DetailActivity
import com.norio.parkeetest.favorite.FavoriteActivity
import com.norio.parkeetest.response.NowPlayingMovieResponse
import com.norio.parkeetest.response.PopularMovieResponse
import com.norio.parkeetest.response.TopRatedMovieResponse

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataPopular()
        setDataTopRated()
        setDataNowPlaying()

        initToolbar()
    }

    private fun initToolbar() {
        binding.includeToolbarHome.toolbar.title = "Movie"
        with(binding.includeToolbarHome.toolbar) {
            inflateMenu(R.menu.menu_home)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.action_favorite) {
                    startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
                }
                true
            }
        }
    }

    private fun setDataPopular() {
        viewModel.popularMovie.observe(this) { popularMovie ->
            if (popularMovie.results != null) {
                val listPopular = ArrayList<PopularMovieResponse.Result>()
                for (popular in popularMovie.results) {
                    popular?.let { listPopular.add(it) }
                }
                val popularAdapter = BannerAdapter(listPopular)
                binding.rvPopularMovie.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvPopularMovie.adapter = popularAdapter

                popularAdapter.setOnBannerClickListener(object :
                    BannerAdapter.OnBannerClickListener {
                    override fun onItemClicked(
                        id: Int?,
                        title: String?,
                        image: String?,
                        date: String?,
                        desc: String?,
                        imagePoster: String?
                    ) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.MOVIE_ID, id)
                        intent.putExtra(DetailActivity.TITLE, title)
                        intent.putExtra(DetailActivity.IMAGE, image)
                        intent.putExtra(DetailActivity.DATE, date)
                        intent.putExtra(DetailActivity.DESCRIPTION, desc)
                        intent.putExtra(DetailActivity.IMAGE_POSTER, imagePoster)
                        startActivity(intent)
                    }

                })
            }
        }
    }

    private fun setDataTopRated() {
        viewModel.topRatedMovie.observe(this) { topRatedMovie ->
            if (topRatedMovie.results != null) {
                val listTopRated = ArrayList<TopRatedMovieResponse.Result>()
                for (topRated in topRatedMovie.results) {
                    topRated?.let { listTopRated.add(it) }
                }
                val topRatedAdapter = TopRatedAdapter(listTopRated)
                binding.rvTopRated.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTopRated.adapter = topRatedAdapter

                topRatedAdapter.setOnTopRatedClickListener(object :
                    TopRatedAdapter.OnTopRatedClickListener {
                    override fun onItemClicked(
                        id: Int?,
                        title: String?,
                        image: String?,
                        date: String?,
                        desc: String?,
                        imagePoster: String?
                    ) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.MOVIE_ID, id)
                        intent.putExtra(DetailActivity.TITLE, title)
                        intent.putExtra(DetailActivity.IMAGE, image)
                        intent.putExtra(DetailActivity.DATE, date)
                        intent.putExtra(DetailActivity.DESCRIPTION, desc)
                        intent.putExtra(DetailActivity.IMAGE_POSTER, imagePoster)
                        startActivity(intent)
                    }

                })
            }
        }
    }

    private fun setDataNowPlaying() {
        viewModel.nowPlayingMovie.observe(this) { nowPlayingMovie ->
            if (nowPlayingMovie.results != null) {
                val listNowPlaying = ArrayList<NowPlayingMovieResponse.Result>()
                for (nowPlaying in nowPlayingMovie.results) {
                    nowPlaying?.let { listNowPlaying.add(it) }
                }
                val nowPlayingAdapter = NowPlayingAdapter(listNowPlaying)
                binding.rvNowPlaying.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvNowPlaying.adapter = nowPlayingAdapter

                nowPlayingAdapter.setOnNowPlayingClickListener(object :
                    NowPlayingAdapter.OnNowPlayingClickListener {
                    override fun onItemClicked(
                        id: Int?,
                        title: String?,
                        image: String?,
                        date: String?,
                        desc: String?,
                        imagePoster: String?
                    ) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.MOVIE_ID, id)
                        intent.putExtra(DetailActivity.TITLE, title)
                        intent.putExtra(DetailActivity.IMAGE, image)
                        intent.putExtra(DetailActivity.DATE, date)
                        intent.putExtra(DetailActivity.DESCRIPTION, desc)
                        intent.putExtra(DetailActivity.IMAGE_POSTER, imagePoster)
                        startActivity(intent)
                    }

                })
            }
        }
    }
}