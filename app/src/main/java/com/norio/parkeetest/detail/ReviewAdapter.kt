package com.norio.parkeetest.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.norio.parkeetest.databinding.RecyclerviewReviewBinding
import com.norio.parkeetest.response.ReviewResponse

/**
 * Created by Norio on 5/18/2023.
 */
class ReviewAdapter(private var listItem: ArrayList<ReviewResponse.Result>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            RecyclerviewReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    class ReviewViewHolder(private val binding: RecyclerviewReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewResponse.Result) {
            binding.tvNameReview.text = data.author
            binding.tvDetailReview.text =
                data.content?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        }
    }
}