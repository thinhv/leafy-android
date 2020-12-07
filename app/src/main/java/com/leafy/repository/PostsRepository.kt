package com.leafy.repository

import GetPlantsQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.leafy.services.Apollo

interface PostsRepository {
    fun loadPosts(): LiveData<Resource<List<GetPlantsQuery.Plant?>>>
}

class PostsRepositoryImpl: PostsRepository {
    private val mutablePosts = MutableLiveData<Resource<List<GetPlantsQuery.Plant?>>>(Resource.loading(null))
    private val apollo = Apollo.apolloClient

    override fun loadPosts(): LiveData<Resource<List<GetPlantsQuery.Plant?>>> {
        mutablePosts.value = Resource.loading(null)
        val query = GetPlantsQuery()
        apollo.query(query).enqueue(object: ApolloCall.Callback<GetPlantsQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                mutablePosts.postValue(Resource.error(e.message ?: "Unknown Error", null))
            }

            override fun onResponse(response: Response<GetPlantsQuery.Data>) {
                mutablePosts.postValue(Resource.success(response.data?.plants ?: listOf<GetPlantsQuery.Plant>()))
            }
        })

        return mutablePosts
    }
}