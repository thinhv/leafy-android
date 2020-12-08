package com.leafy.ui.me

import GetPlantsQuery
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafy.R
import com.leafy.UserViewModel
import com.leafy.ui.feeds.PostListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.android.synthetic.main.fragment_me.*


class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var postListAdapter: UserPostListAdapter
    private val picId = 123


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel = ViewModelProviders.of(this).get(MeViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_me, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        button_add.setOnClickListener {
//            activity?.let {
//                val add_plant_intent = Intent(it, AddPlantActivity::class.java)
//                it.startActivity(add_plant_intent)
//            }
//        }
        setupAdapter()
        setupObservers()

        button_logout.setOnClickListener {
            userViewModel.logout()
        }

        userViewModel.getProfile().observe(viewLifecycleOwner, {
            text_username.text = it.data?.user?.username
            it.data?.user?.profileImageUrl?.let {
                Picasso.get().load(Uri.parse(it)).into(profile_image)
            }
        })
    }

    private fun setupObservers() {
        userViewModel.getProfile().observe(viewLifecycleOwner, Observer {
            val posts = it.data?.plants ?: listOf()
            postListAdapter.posts = posts
            text_trophy.text = "Congratulations! You have collected ${posts.size} plants!"
        })
    }

    private fun setupAdapter() {
        postListAdapter = UserPostListAdapter()
        val layoutManager = LinearLayoutManager(activity)
        user_post_list_rc.layoutManager = layoutManager
        user_post_list_rc.addItemDecoration(
            DividerItemDecoration(
                activity,
                layoutManager.orientation
            )
        )
        user_post_list_rc.adapter = postListAdapter
    }
}