package com.leafy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leafy.repository.Resource
import com.leafy.repository.UserRepositoryImpl

class UserViewModel: ViewModel() {
    val isLoggedIn: LiveData<Boolean>
        get() = userRepository.isLoggedIn

    private val userRepository = UserRepositoryImpl()

    fun login(username: String, password: String): LiveData<Resource<UserQuery.Login>> {
        return userRepository.login(username, password)
    }

    fun register(username: String, email: String, password: String): LiveData<Resource<RegisterUserMutation.RegisterUser>> {
        return userRepository.register(username = username, email = email, password = password)
    }

    fun logout() {

    }
}