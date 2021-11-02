package com.example.multi.retrofitapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MainService {

    @GET("posts")
   // suspend fun getPosts(): Response<List<ResponsePostItem>>
    suspend fun getPosts(): List<ResponsePostItem>
}

//    @POST("posts")
//    fun postData(@Body data: Post): Call<ResponsePostItem>

fun MainService():MainService{
    val okHttpClientBuilder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BASIC
    okHttpClientBuilder.addInterceptor(logging)
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        //.client(okHttpClientBuilder.build())
        .client(okHttpClientBuilder.build())
        .build()
        .create(MainService::class.java)
}