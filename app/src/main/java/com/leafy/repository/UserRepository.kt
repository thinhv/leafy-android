package com.leafy.repository

import RegisterUserMutation
import UserProfileQuery
import UserQuery
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.exception.ApolloException
import com.leafy.LeafyApplication
import com.leafy.db.LeafyDB
import com.leafy.db.entities.UserData
import com.leafy.models.User
import com.leafy.services.Apollo

interface UserRepository {
    val isLoggedIn: LiveData<Boolean>
    val token: LiveData<String?>
    fun login(username: String, password: String): LiveData<Resource<UserQuery.Login>>
    fun register(username: String, email: String, password: String): LiveData<Resource<RegisterUserMutation.RegisterUser>>
    fun logout()
    fun getProfile(): LiveData<Resource<UserProfileQuery.UserProfile>>
}

class UserRepositoryImpl: UserRepository {
    private val apollo = Apollo.apolloClient
    private val database: LeafyDB = LeafyDB.getInstance(LeafyApplication.context)
    private val mutableLoginUser: MutableLiveData<Resource<UserQuery.Login>> = MutableLiveData(Resource.loading(null))
    private val mutableRegisterUser: MutableLiveData<Resource<RegisterUserMutation.RegisterUser>> = MutableLiveData(Resource.loading(null))
    private val mutableUserProfile: MutableLiveData<Resource<UserProfileQuery.UserProfile>> = MutableLiveData(Resource.loading(null))
    private val localUserData: LiveData<UserData?> = Transformations.map(database.userDataDao().getAll(), {
        if (it.isEmpty()) {
            return@map null
        }
        return@map it.first()
    })

    override val isLoggedIn: LiveData<Boolean>
        get() = Transformations.map(localUserData, {
            return@map it != null
        })

    override val token: LiveData<String?>
        get() = Transformations.map(mutableLoginUser, {
            return@map it.data?.token
        })

    init {
    }

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
                } ?: {
                    mutableLoginUser.postValue(Resource.success(response.data?.login))
                    database.userDataDao().deleteAll()
                    response.data?.login?.let {
                        database.userDataDao().insert(UserData(token = it.token ?: "", userId = it.id, username = it.username))
                    }
                }()
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
        database.userDataDao().deleteAll()
    }

    override fun getProfile(): LiveData<Resource<UserProfileQuery.UserProfile>> {
        mutableUserProfile.value = Resource.loading(null)
        return Transformations.switchMap(localUserData, { userdata ->
            if (userdata?.username == null) {
                return@switchMap MutableLiveData<Resource<UserProfileQuery.UserProfile>>().apply { postValue(Resource.error("Username not found", null)) }
            }

            val query = UserProfileQuery(username = userdata.username)
            apollo.query(query).enqueue(object : ApolloCall.Callback<UserProfileQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    mutableUserProfile.postValue(Resource.error(e.message ?: "Unknown Error", null))
                }

                override fun onResponse(response: com.apollographql.apollo.api.Response<UserProfileQuery.Data>) {
                    response.errors?.let {
                        mutableUserProfile.postValue(Resource.error(it.first().message, null))
                    } ?: {
                        mutableUserProfile.postValue(Resource.success(response.data?.userProfile))

                    }()
                }
            })

            return@switchMap this.mutableUserProfile
        })
    }
}