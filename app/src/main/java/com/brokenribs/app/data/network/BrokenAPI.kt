package com.brokenribs.app.data.network

import com.brokenribs.app.data.network.reponse.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BrokenAPI {


    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>



    companion object{

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): BrokenAPI {
            val  okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            val urlBase = "https://api.simplifiedcoding.in/course-apis/mvvm/"
            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BrokenAPI::class.java)
        }
    }
}