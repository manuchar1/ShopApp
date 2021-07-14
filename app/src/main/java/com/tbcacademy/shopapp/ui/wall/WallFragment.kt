package com.tbcacademy.shopapp.ui.wall

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.snackbar
import com.tbcacademy.shopapp.databinding.FragmentWallBinding
import com.tbcacademy.shopapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallFragment : BaseFragment<FragmentWallBinding>(FragmentWallBinding::inflate) {

    private val viewModel: WallViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observes()
        initPosts()
        setListeners()

    }

    private fun initPosts(){
        viewModel.getPosts()
    }

    private fun setListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getPosts()
        }
    }

    private fun observes(){

        viewModel.postLiveData.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.swipeToRefresh.isRefreshing = false
                snackbar(it)

            },
            onLoading = {
                binding.swipeToRefresh.isRefreshing

            }

        ) {
            //binding.registerProgress.isVisible = false

        })
    }



}