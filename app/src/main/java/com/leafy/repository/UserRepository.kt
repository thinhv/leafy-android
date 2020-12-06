package com.leafy.repository

import UserQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.exception.ApolloException
import com.leafy.models.User
import com.leafy.services.Apollo

interface UserRepository {
    val isLoggedIn: LiveData<Boolean>
    val token: LiveData<String?>
    fun login(username: String, password: String): LiveData<Resource<UserQuery.Login>>
    fun logout()
}

class UserRepositoryImpl: UserRepository {
    private val apollo = Apollo.apolloClient

    private val mutableLoginUser: MutableLiveData<Resource<UserQuery.Login>> = MutableLiveData(Resource.loading(null))

    override val isLoggedIn: LiveData<Boolean>
        get() = Transformations.map(mutableLoginUser, {
            return@map it.data != null
        })

    override val token: LiveData<String?>
        get() = Transformations.map(mutableLoginUser, {
            return@map it.data?.token
        })

    override fun login(username: String, password: String): LiveData<Resource<UserQuery.Login>> {
        mutableLoginUser.value = Resource.loading(null)
        val query = UserQuery(username = username, password = password)
        apollo.query(query).enqueue(object: ApolloCall.Callback<UserQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                mutableLoginUser.postValue(Resource.error("", null))
            }

            override fun onResponse(response: com.apollographql.apollo.api.Response<UserQuery.Data>) {
                mutableLoginUser.postValue(Resource.success(response.data?.login))
            }
        })

        return mutableLoginUser
    }

    override fun logout() {

    }
}