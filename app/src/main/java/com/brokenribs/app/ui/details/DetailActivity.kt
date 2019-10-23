package com.brokenribs.app.ui.details

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.brokenribs.app.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uriSource: String = intent.getStringExtra("uriSource")
        val title: String = intent.getStringExtra("title")
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = title

        if (uriSource != null) {
            Glide.with(this)//.asGif()
                .load("$uriSource?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260") //.apply(RequestOptions.circleCropTransform())
                .into(imageViewDetail)

        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
