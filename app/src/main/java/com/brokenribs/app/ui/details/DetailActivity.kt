package com.brokenribs.app.ui.details

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.brokenribs.app.R
import com.brokenribs.app.util.snackbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.container_frament.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.coroutines.delay
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uriSource: String = intent.getStringExtra("uriSource")
        val title: String = intent.getStringExtra("title")
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        titleTextView.text = title

        if (uriSource != null) {
            Glide.with(this)//.asGif()
                .load("$uriSource?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260") //.apply(RequestOptions.circleCropTransform())
                .into(imageViewDetail)

        }

        fab.setOnClickListener { view ->

            layout_main.snackbar("Adding product ...")

            Handler().postDelayed({
                layout_main.snackbar("Added to cart ...")
            }, 1000)
        }



    }
}
