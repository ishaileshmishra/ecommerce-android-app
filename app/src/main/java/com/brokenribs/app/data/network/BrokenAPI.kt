package com.brokenribs.app.data.network

import com.brokenribs.app.data.network.reponse.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
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


    companion object{
        operator fun invoke(): BrokenAPI {
            val urlBase = "https://api.simplifiedcoding.in/course-apis/mvvm/"
            return  Retrofit.Builder().baseUrl(urlBase).addConverterFactory(GsonConverterFactory.create()).build().create(
                BrokenAPI::class.java)
        }
    }
}