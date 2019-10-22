package com.brokenribs.app.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}



fun ProgressBar.hide(){
    visibility = View.INVISIBLE
}

fun AppCompatImageView.setImageDrawable(icShoppingBasketIcon: Int) {
    this.setImageResource(icShoppingBasketIcon)
}