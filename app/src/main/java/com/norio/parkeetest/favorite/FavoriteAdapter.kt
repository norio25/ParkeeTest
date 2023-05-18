package com.norio.parkeetest.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.norio.parkeetest.database.Favorite
import com.norio.parkeetest.databinding.RecyclerviewFavoriteBinding

/**
 * Created by Norio on 5/18/2023.
 */
class FavoriteAdapter(private var listItem: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            RecyclerviewFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    class FavoriteViewHolder(private val binding: RecyclerviewFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Favorite) {
            Glide.with(itemView.context).load(data.image).into(binding.ivFavorite)
            binding.tvTitleFavorite.text = data.title
            binding.tvDateFavorite.text = data.date
            binding.tvDescFavorite.text = data.description
        }
    }
}