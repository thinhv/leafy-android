package com.leafy.ui.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafy.R
import kotlinx.android.synthetic.main.fragment_feeds.*

class FeedsFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var postListAdapter: PostListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_feeds, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        homeViewModel.loadPost().observe(viewLifecycleOwner, Observer {
            postListAdapter.posts = it.data ?: listOf<GetPlantsQuery.Plant?>()
        })
    }

    private fun setupAdapter() {
        postListAdapter = PostListAdapter()
        val layoutManager = LinearLayoutManager(activity)
        post_list_rc.layoutManager = layoutManager
        post_list_rc.addItemDecoration(
            DividerItemDecoration(
                activity,
                layoutManager.orientation
            )
        )
        post_list_rc.adapter = postListAdapter
    }
}