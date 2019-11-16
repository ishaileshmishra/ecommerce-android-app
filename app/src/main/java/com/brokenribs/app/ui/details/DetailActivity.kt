package com.brokenribs.app.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.brokenribs.app.R
import com.brokenribs.app.util.ImagesUtils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.product_description_layout.*
import kotlinx.android.synthetic.main.products_img_layout.*


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val uriSource = intent.getStringExtra("uriSource")
        val title  = intent.getStringExtra("title")
        textViewTitle.text = title
        val images = ImagesUtils.getImages.scrollImages();
        images.add(0, uriSource)
        val productImagesAdapter = ProductImagesAdapter(images.toMutableList())
        productImagesViewPager.adapter = productImagesAdapter
        view_pager_indicator.setupWithViewPager(productImagesViewPager, true)

        floatingWishlist.setOnClickListener { }
        viewPagerProductDetails.adapter = ProductDetailsAdapter(supportFragmentManager, tabLayoutProductDescription.tabCount)
        viewPagerProductDetails!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutProductDescription))
        tabLayoutProductDescription!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerProductDetails!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.main_search_icon){ return true }
        else if (id == R.id.main_cart_icon){ return true }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search_cart_item, menu)
        return true
    }



}
