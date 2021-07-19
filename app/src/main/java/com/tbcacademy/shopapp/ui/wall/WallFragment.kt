package com.tbcacademy.shopapp.ui.wall

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbcacademy.shopapp.adapter.PostAdapter
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.snackbar
import com.tbcacademy.shopapp.databinding.FragmentWallBinding
import com.tbcacademy.shopapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallFragment : BaseFragment<FragmentWallBinding>(FragmentWallBinding::inflate) {

    private val viewModel: WallViewModel by viewModels()
    private lateinit var postAdapter: PostAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hideBottomNavigation()
        observes()
        initPosts()
        setListeners()


    }

    private fun initPosts() {
        viewModel.getPosts()
        postAdapter = PostAdapter()
        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPosts.adapter = postAdapter
    }

    private fun setListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getPosts()
        }
    }

    private fun observes() {

        viewModel.postLiveData.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.swipeToRefresh.isRefreshing = false
                snackbar(it)

            },
            onLoading = {
                binding.swipeToRefresh.isRefreshing = true

            }

        ) {
            postAdapter.setData(it.toMutableList())
            binding.swipeToRefresh.isRefreshing = false

        })
    }


}