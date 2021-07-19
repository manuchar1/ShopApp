package com.tbcacademy.shopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tbcacademy.shopapp.base.goneIf
import com.tbcacademy.shopapp.databinding.ItemRecyclerBinding
import com.tbcacademy.shopapp.network.models.Post

class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val posts: MutableList<Post> = mutableListOf()


    inner class ViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Post
        private lateinit var viewPagerAdapter: ViewPagerAdapter
        fun bind() {
            model = posts[adapterPosition]
            initViewPagerAdapter()
            setData()
        }

        private fun setData() {
            binding.product.text = model.title
            binding.tvDescription.text = model.description
            binding.tvPrice.text = "$ ${model.price.toString()}"


        }

        private fun initViewPagerAdapter() {
            viewPagerAdapter = model.urls?.let { ViewPagerAdapter(it.toMutableList()) }!!
            binding.viewPager.adapter = viewPagerAdapter
            binding.viewPager.goneIf(model.urls.isNullOrEmpty())
            val size: Int = model.urls?.size ?: 0
            binding.iBtnRight.goneIf(size < 2)
            binding.iBtnLeft.goneIf(size < 2)
            binding.pageNumber.goneIf(size < 2)
            if (size >= 2)
                setCurrentPageIndex(1)
            binding.viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentPageIndex(position + 1)
                }
            })

        }

        private fun setCurrentPageIndex(index: Int) {
            binding.pageNumber.text = "$index/${model.urls?.size}"


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun getItemCount() = posts.size

    fun setData(posts: MutableList<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()


    }

}



