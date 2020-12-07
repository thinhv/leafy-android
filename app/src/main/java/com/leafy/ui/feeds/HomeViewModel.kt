package com.leafy.ui.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leafy.R
import com.leafy.domain.Post
import com.leafy.repository.PostsRepository
import com.leafy.repository.PostsRepositoryImpl
import com.leafy.repository.Resource
import java.util.*

class HomeViewModel : ViewModel() {
    private val postsRepository:PostsRepository = PostsRepositoryImpl()

    fun loadPost(): LiveData<Resource<List<GetPlantsQuery.Plant?>>> = postsRepository.loadPosts()
}