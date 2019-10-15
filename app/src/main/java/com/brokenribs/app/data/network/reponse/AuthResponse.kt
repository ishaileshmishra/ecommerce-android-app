package com.brokenribs.app.data.network.reponse

import com.brokenribs.app.data.db.entities.User

data class AuthResponse (

    val isSuccessful: Boolean?,
    val message : String?,
    val user: User?
)