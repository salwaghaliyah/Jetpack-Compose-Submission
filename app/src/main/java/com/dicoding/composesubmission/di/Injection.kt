package com.dicoding.composesubmission.di

import com.dicoding.composesubmission.data.Repository
import com.dicoding.composesubmission.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}