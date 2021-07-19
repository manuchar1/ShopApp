package com.tbcacademy.shopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tbcacademy.shopapp.base.setImage
import com.tbcacademy.shopapp.databinding.ItemImageBinding
import com.tbcacademy.shopapp.network.models.Post

class ViewPagerAdapter(private val images: MutableList<Post.ImageItem>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.imageView.setImage(images[adapterPosition].url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun getItemCount() = images.size


}