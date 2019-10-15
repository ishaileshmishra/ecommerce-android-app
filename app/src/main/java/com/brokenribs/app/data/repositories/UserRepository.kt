package com.brokenribs.app.data.repositories

import com.brokenribs.app.data.network.BrokenAPI
import com.brokenribs.app.data.network.SafeApiRequest
import com.brokenribs.app.data.network.reponse.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { (BrokenAPI.invoke().userLogin(email, password))}
    }

}