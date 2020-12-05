package com.leafy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    val isLoggedIn: LiveData<Boolean>

    private val mutableIsLoggedIn = MutableLiveData<Boolean>()

    init {
        mutableIsLoggedIn.value = true
        isLoggedIn = mutableIsLoggedIn
    }

    fun login() {
    }
}