package com.leafy.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leafy.R
import com.leafy.domain.DashboardItem

class MeViewModel : ViewModel() {

    private val _items = MutableLiveData<List<DashboardItem>>().apply {
        value = listOf(
            DashboardItem(R.drawable.plant1, "Easter Lily"),
            DashboardItem(R.drawable.plant3, "Elephant Ears"),
            DashboardItem(R.drawable.plant4, "Elephant Ears"),
            DashboardItem(R.drawable.plant5, "Easter Lily")
        )
    }
    val items: LiveData<List<DashboardItem>> = _items
}