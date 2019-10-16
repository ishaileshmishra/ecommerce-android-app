package com.brokenribs.app.data.repositories

import com.brokenribs.app.data.db.AppDatabase
import com.brokenribs.app.data.db.entities.User
import com.brokenribs.app.data.network.BrokenAPI
import com.brokenribs.app.data.network.NetworkConnectionInterceptor
import com.brokenribs.app.data.network.SafeApiRequest
import com.brokenribs.app.data.network.reponse.AuthResponse
import retrofit2.Response

class UserRepository(

    private val api: BrokenAPI,
    private val db: AppDatabase

) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { (api.userLogin(email, password))}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}