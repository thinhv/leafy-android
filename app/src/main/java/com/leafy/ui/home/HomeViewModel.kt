package com.leafy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leafy.R
import com.leafy.domain.Post
import java.util.*

class HomeViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>().apply {
        value = listOf(
            Post(Date(), "Plant 1", R.drawable.plant1, R.drawable.profileimage1, "Ballari Qacha"),
            Post(Date(), "Plant 2", R.drawable.plant, R.drawable.profileimage2, "David Alaba"),
            Post(Date(), "Plant 3", R.drawable.plant3, R.drawable.profileimage3, "Thierry Henry"),
            Post(Date(), "Plant 4", R.drawable.plant4, R.drawable.profileimage4, "Nicolas Anelka"),
            Post(Date(), "Plant 5", R.drawable.plant5, R.drawable.profileimage5, "Andrea Rajacic"),
            Post(Date(), "Plant 6", R.drawable.plant6, R.drawable.profileimage6, "Aubameyang"),
            Post(Date(), "Plant 7", R.drawable.plant7, R.drawable.profileimage7, "Janne Ahonen"),
            Post(Date(), "Plant 8", R.drawable.plant8, R.drawable.profileimage8, "Antti Niemi"),
            Post(Date(), "Plant 9", R.drawable.plant9, R.drawable.profileimage9, "Kari Lehtonen"),
        )
    }
    val posts: LiveData<List<Post>> = _posts
}