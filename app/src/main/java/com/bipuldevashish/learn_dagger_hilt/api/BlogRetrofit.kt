package com.bipuldevashish.learn_dagger_hilt.api

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun  get(): List<BlogNetworkEntity>
}