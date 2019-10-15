package com.brokenribs.app.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brokenribs.app.R
import com.brokenribs.app.data.db.entities.User
import com.brokenribs.app.databinding.ActivityLoginBinding
import com.brokenribs.app.ui.home.HomeActivity
import com.brokenribs.app.util.hide
import com.brokenribs.app.util.show
import com.brokenribs.app.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , AuthListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

    }


    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        toast("${user.name} logged In")
        progressBar.hide()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        toast("Login Failed")
    }

    override fun onSignupBtnTap() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}

