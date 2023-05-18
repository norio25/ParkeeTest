package com.norio.parkeetest.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.norio.parkeetest.databinding.RecyclerviewBannerBinding
import com.norio.parkeetest.response.PopularMovieResponse

/**
 * Created by Norio on 5/17/2023.
 */
class BannerAdapter(private val listItem: ArrayList<PopularMovieResponse.Result>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private var onBannerClickListener: OnBannerClickListener? = null

    fun setOnBannerClickListener(onBannerClickListener: OnBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding =
            RecyclerviewBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class BannerViewHolder(private val binding: RecyclerviewBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PopularMovieResponse.Result) {
            val imageBanner = "https://image.tmdb.org/t/p/original${data.backdropPath}"
            Glide.with(itemView.context).load(imageBanner).into(binding.ivBanner)
            val imagePoster = "https://image.tmdb.org/t/p/original${data.posterPath}"

            itemView.setOnClickListener {
                onBannerClickListener?.onItemClicked(
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

    interface OnBannerClickListener {
        fun onItemClicked(id: Int?, title: String?, image: String?, date: String?, desc: String?, imagePoster: String?)
    }
}