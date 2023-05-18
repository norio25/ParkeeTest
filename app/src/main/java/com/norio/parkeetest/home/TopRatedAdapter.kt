package com.norio.parkeetest.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.norio.parkeetest.databinding.RecyclerviewMovieBinding
import com.norio.parkeetest.response.TopRatedMovieResponse

/**
 * Created by Norio on 5/18/2023.
 */
class TopRatedAdapter(private var listItem: ArrayList<TopRatedMovieResponse.Result>) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    private var onTopRatedClickListener: OnTopRatedClickListener? = null

    fun setOnTopRatedClickListener(onTopRatedClickListener: OnTopRatedClickListener) {
        this.onTopRatedClickListener = onTopRatedClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val binding =
            RecyclerviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class TopRatedViewHolder(private val binding: RecyclerviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TopRatedMovieResponse.Result) {
            val imageBanner = "https://image.tmdb.org/t/p/original${data.backdropPath}"
            val imagePoster = "https://image.tmdb.org/t/p/original${data.posterPath}"
            Glide.with(itemView.context).load(imagePoster).into(binding.ivMovie)
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.releaseDate

            itemView.setOnClickListener {
                onTopRatedClickListener?.onItemClicked(
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

    interface OnTopRatedClickListener {
        fun onItemClicked(id: Int?, title: String?, image: String?, date: String?, desc: String?, imagePoster: String?)
    }
}