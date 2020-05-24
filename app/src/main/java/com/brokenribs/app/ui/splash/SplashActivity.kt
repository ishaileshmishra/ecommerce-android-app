package com.brokenribs.app.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.brokenribs.app.R
import com.brokenribs.app.ui.auth.LoginActivity
import com.brokenribs.app.ui.home.HomeActivity

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pauseToLaunch();
    }

    private fun pauseToLaunch(){

        Handler().postDelayed(Runnable
        {
            if (!checkUserLoggedIn()){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("isLoggedIn", true)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("isLoggedIn", true)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }

    private fun checkUserLoggedIn(): Boolean {
        // check condition here.
        return false
    }


    private fun rotateAnimation(){

    }

}
