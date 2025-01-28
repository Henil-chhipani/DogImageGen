package com.henil.dogimagegen.data.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.henil.dogimagegen.data.api.DogApiService
import com.squareup.moshi.Moshi

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dog.ceo/api/"

    // Provide Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide DogApiService instance
    @Provides
    @Singleton
    fun provideDogApiService(retrofit: Retrofit): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
