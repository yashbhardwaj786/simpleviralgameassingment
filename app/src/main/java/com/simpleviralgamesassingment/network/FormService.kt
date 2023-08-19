package com.simpleviralgamesassingment.network

import com.simpleviralgamesassingment.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface FormService {
    @GET("breeds/image/random")
    suspend fun generateDogImages(): Response<DogBreedResult>
}
