package com.leafy.repository

import RegisterUserMutation
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
    fun register(username: String, email: String, password: String): LiveData<Resource<RegisterUserMutation.RegisterUser>>
    fun logout()
}

class UserRepositoryImpl: UserRepository {
    private val apollo = Apollo.apolloClient

    private val mutableLoginUser: MutableLiveData<Resource<UserQuery.Login>> = MutableLiveData(Resource.loading(null))
    private val mutableRegisterUser: MutableLiveData<Resource<RegisterUserMutation.RegisterUser>> = MutableLiveData(Resource.loading(null))

    override val isLoggedIn: LiveData<Boolean>
        get() = Transformations.map(mutableLoginUser, {
            return@map it.data?.token != null
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
                mutableLoginUser.postValue(Resource.error(e.message ?: "Unknown Error", null))
            }

            override fun onResponse(response: com.apollographql.apollo.api.Response<UserQuery.Data>) {
                response.errors?.let {
                    mutableLoginUser.postValue(Resource.error(it.first().message, null))
                } ?: mutableLoginUser.postValue(Resource.success(response.data?.login))
            }
        })

        return mutableLoginUser
    }

    override fun register(username: String, email: String, password: String): LiveData<Resource<RegisterUserMutation.RegisterUser>> {
        mutableRegisterUser.value = Resource.loading(null)
        val mutation = RegisterUserMutation(username = username, email = email, password = password, firstName = "", lastName = "")
        apollo.mutate(mutation).enqueue(object: ApolloCall.Callback<RegisterUserMutation.Data>(){
            override fun onFailure(e: ApolloException) {
                mutableRegisterUser.postValue(Resource.error(e.message ?: "Unknown Error", null))
            }

            override fun onResponse(response: com.apollographql.apollo.api.Response<RegisterUserMutation.Data>) {
                response.errors?.let {
                    mutableRegisterUser.postValue(Resource.error(it.first().message, null))
                } ?: mutableRegisterUser.postValue(Resource.success(response.data?.registerUser))
            }
        })
        return mutableRegisterUser
    }

    override fun logout() {

    }
}