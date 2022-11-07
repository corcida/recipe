package com.yape.recipe.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yape.recipe.data.network.RecipeRemoteDataSource
import com.yape.recipe.data.network.RecipeService
import com.yape.recipe.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideRecipeService(retrofit: Retrofit):
            RecipeService = retrofit.create(RecipeService::class.java)

    @Provides
    fun provideRecipeRemoteDataSource(service: RecipeService):
            RecipeRemoteDataSource = RecipeRemoteDataSource(service)

}