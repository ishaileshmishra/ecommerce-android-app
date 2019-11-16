package com.brokenribs.app.ui.auth
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.brokenribs.app.data.repositories.UserRepository
import com.brokenribs.app.util.APIException
import com.brokenribs.app.util.Coroutines
import com.brokenribs.app.util.NoInternetException


class LoginViewModel(private val repository: UserRepository): ViewModel() {

    var emailAddress: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginClick(view: View){
        authListener?.onStarted()
        if (emailAddress.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email or Password")
            return
        }


        Coroutines.main {
            try {
                val loginResponse = repository.userLogin(emailAddress!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailure(loginResponse.message!!)
            }catch (e: APIException){
                authListener?.onFailure("Error code ${e.message!!}")
            }catch (e: NoInternetException){
                authListener?.onFailure("Error code ${e.message!!}")
            }

        }

    }


    fun onSignupClick(view: View){
        view.context.startActivity(Intent(view.context, SignUpActivity::class.java))
        //authListener?.onSignupBtnTap()
    }

}