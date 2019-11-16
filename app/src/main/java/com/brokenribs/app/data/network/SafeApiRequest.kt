package com.brokenribs.app.data.network

import com.brokenribs.app.util.APIException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun <T: Any> apiRequest(call: suspend ()-> Response<T>): T {

        val response = call.invoke()

        if (response.isSuccessful) { return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) { }
                    message.append("\n")
            }

            message.append("Error code: ${response.code()}")
            throw APIException(message.toString())
        }
    }
}