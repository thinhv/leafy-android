package com.leafy.ui.me

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.leafy.R
import com.leafy.UserViewModel
import com.leafy.models.User
import com.leafy.repository.Resource
import com.leafy.ui.addplant.AddPlantActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_register.*


class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: DashboardListAdapter
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
//        setupAdapter()
//        setupObservers()

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

//    private fun setupObservers() {
//        meViewModel.items.observe(viewLifecycleOwner, Observer {
//            adapter.items = it
//        })
//    }

//    private fun setupAdapter() {
//        adapter = DashboardListAdapter()
//        val layoutManager = LinearLayoutManager(activity)
//        plant_list_rc.layoutManager = layoutManager
//        plant_list_rc.addItemDecoration(
//            DividerItemDecoration(
//                activity,
//                layoutManager.orientation
//            )
//        )
//        plant_list_rc.adapter = adapter
//    }
}