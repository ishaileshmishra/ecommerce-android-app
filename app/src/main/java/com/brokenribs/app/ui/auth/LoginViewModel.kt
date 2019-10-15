package com.brokenribs.app.ui.auth
import android.view.View
import androidx.lifecycle.ViewModel
import com.brokenribs.app.data.repositories.UserRepository
import com.brokenribs.app.util.APIException
import com.brokenribs.app.util.Coroutines


class LoginViewModel: ViewModel() {


    var emailAddress: String? = null
    var password: String? = null
    var authListener: AuthListener? = null


    fun onLoginClick(view: View){
        authListener?.onStarted()
        if (emailAddress.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email or Password")
            return
        }


        Coroutines.main {
            try {
                val loginResponse = UserRepository().userLogin(emailAddress!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }

                authListener?.onFailure(loginResponse.message!!)
            }catch (e: APIException){
                authListener?.onFailure("Error code ${e.message!!}")
            }

        }

    }


    fun onSignupClick(view: View){
        authListener?.onSignupBtnTap()
    }

}