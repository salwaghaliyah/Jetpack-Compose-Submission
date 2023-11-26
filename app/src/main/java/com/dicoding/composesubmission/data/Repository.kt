package com.dicoding.composesubmission.data

import android.util.Log
import com.dicoding.composesubmission.data.response.ResultsItem
import com.dicoding.composesubmission.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository private constructor(
    private val apiService: ApiService
){
    private val character = mutableListOf<ResultsItem>()

    suspend fun getAllCharacter() : Flow<List<ResultsItem>> {
        val getCharDeffered = apiService.getAllCharacter()
        try {
            val nonNullResults = getCharDeffered.results.orEmpty().filterNotNull()
            character.addAll(nonNullResults)
        } catch (e: Exception) {
            Log.e(TAG, "onFailure: ${e.message.toString()}")
        }
        return flowOf(character)
    }

    suspend fun getCharacterById(charId: Int): ResultsItem {
        val response = apiService.getCharacterById(charId)
        return response.body() ?: throw CharacterNotFoundException("Character not found for id: $charId")
    }


    companion object {
        private const val TAG = "MainActivity"

        private var instance: Repository? = null
        fun getInstance(apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }
}

class CharacterNotFoundException(message: String) : Exception(message)