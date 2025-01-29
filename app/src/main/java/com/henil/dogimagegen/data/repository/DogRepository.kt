package com.henil.dogimagegen.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.henil.dogimagegen.data.LruCache
import com.henil.dogimagegen.data.api.DogApiService
import com.henil.dogimagegen.data.api.DogImageResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences

@Singleton
class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dataStore: DataStore<Preferences>,
    private val moshi: Moshi // JSON parser
) {

    private val lruCache = LruCache<String, String>(20)
    private val imagesKey = stringPreferencesKey("dog_images")

    private val jsonAdapter = moshi.adapter<List<String>>(
        Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
    )

    init {
        loadCacheFromStorage()
    }
    suspend fun fetchAndStoreDogImage(): DogImageResponse {
        return try {
            val response = dogApiService.getRandomDogImage() // Non-blocking
            lruCache.put(response.message, response.message)
            saveCacheToStorage()
            response
        } catch (e: Exception) {
            DogImageResponse(message = "Error: ${e.localizedMessage}", status = "error")
        }
    }


    fun getAllImagesFlow(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[imagesKey] ?: "[]"
            jsonAdapter.fromJson(jsonString) ?: emptyList()
        }
    }

    fun clearAllImages() {
        lruCache.clear()
        saveCacheToStorage()
    }

    private fun saveCacheToStorage() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                val jsonString = jsonAdapter.toJson(lruCache.getAll().reversed())
                preferences[imagesKey] = jsonString
            }
        }
    }

    private fun loadCacheFromStorage() {
        CoroutineScope(Dispatchers.IO).launch {
            getAllImagesFlow().firstOrNull()?.forEach { imageUrl ->
                lruCache.put(imageUrl, imageUrl)
            }
        }
    }
}
