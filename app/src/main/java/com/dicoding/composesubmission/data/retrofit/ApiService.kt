package com.dicoding.composesubmission.data.retrofit

import com.dicoding.composesubmission.data.response.ResultsItem
import com.dicoding.composesubmission.data.response.RickResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getAllCharacter(): RickResponse

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<ResultsItem>

}