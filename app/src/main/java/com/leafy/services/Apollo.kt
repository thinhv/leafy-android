package com.leafy.services

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object Apollo {
//    private const val BASE_URL = "https://leafy-backend.herokuapp.com/graphql"
    private const val BASE_URL = "http://localhost:3000/graphql"

    private val okHttpClient = OkHttpClient.Builder().build()

    val apolloClient =
            ApolloClient.builder()
                    .serverUrl(BASE_URL)
                    .okHttpClient(okHttpClient)
                    .build()
}