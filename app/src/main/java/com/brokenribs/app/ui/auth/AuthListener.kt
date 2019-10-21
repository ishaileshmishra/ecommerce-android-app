package com.brokenribs.app.ui.auth
import com.brokenribs.app.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailure(message: String)

}