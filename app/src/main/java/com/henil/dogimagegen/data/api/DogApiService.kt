package com.henil.dogimagegen.data.api

import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {
    //    @GET("breeds/image/random")
//    fun getRandomDogImage():  Call<DogImageResponse>
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogImageResponse

}