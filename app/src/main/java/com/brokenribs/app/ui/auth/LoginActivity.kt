package com.brokenribs.app.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brokenribs.app.R
import com.brokenribs.app.data.db.entities.User
import com.brokenribs.app.databinding.ActivityLoginBinding
import com.brokenribs.app.ui.home.HomeActivity
import com.brokenribs.app.util.hide
import com.brokenribs.app.util.show
import com.brokenribs.app.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity() , AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->

            if (user!=null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })

    }


    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        root_layout.snackbar("${user.name} logged In")
        progressBar.hide()
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        root_layout.snackbar(message)
    }


}

