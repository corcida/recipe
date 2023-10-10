package com.corcida.recipe.framework.remote

import com.corcida.recipe.framework.remote.service.RecipeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeApi {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val recipeService: RecipeService = Retrofit.Builder()
        .baseUrl("http://demo8268743.mockable.io/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(RecipeService::class.java)
        }
}