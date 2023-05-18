package com.norio.parkeetest.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.norio.parkeetest.databinding.RecyclerviewMovieBinding
import com.norio.parkeetest.response.NowPlayingMovieResponse

/**
 * Created by Norio on 5/18/2023.
 */
class NowPlayingAdapter(private var listItem: ArrayList<NowPlayingMovieResponse.Result>) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPLayingViewHolder>() {

    private var onNowPlayingClickListener: OnNowPlayingClickListener? = null

    fun setOnNowPlayingClickListener(onNowPlayingClickListener: OnNowPlayingClickListener) {
        this.onNowPlayingClickListener = onNowPlayingClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPLayingViewHolder {
        val binding =
            RecyclerviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPLayingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPLayingViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class NowPLayingViewHolder(private val binding: RecyclerviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NowPlayingMovieResponse.Result) {
            val imageBanner = "https://image.tmdb.org/t/p/original${data.backdropPath}"
            val imagePoster = "https://image.tmdb.org/t/p/original${data.posterPath}"
            Glide.with(itemView.context).load(imagePoster).into(binding.ivMovie)
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.releaseDate

            itemView.setOnClickListener {
                onNowPlayingClickListener?.onItemClicked(
                    data.id,
                    data.title,
                    imageBanner,
                    data.releaseDate,
                    data.overview,
                    imagePoster
                )
            }
        }
    }

    interface OnNowPlayingClickListener {
        fun onItemClicked(id: Int?, title: String?, image: String?, date: String?, desc: String?, imagePoster: String?)
    }
}