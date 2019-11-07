package com.brokenribs.app.ui.details

import android.content.res.ColorStateList
import android.graphics.Color
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

    private val pagerImages = ArrayList<String>()
    private var ADDED_TO_WISHLIST: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        pagerImages.add("https://images.pexels.com/photos/2847037/pexels-photo-2847037.jpeg")
//        pagerImages.add("https://images.pexels.com/photos/2737022/pexels-photo-2737022.jpeg")
//        pagerImages.add("https://images.pexels.com/photos/2977304/pexels-photo-2977304.jpeg")
//        pagerImages.add("https://images.pexels.com/photos/2776911/pexels-photo-2776911.jpeg")

        val images: Array<String> = ImagesUtils.getImageUrls.getImageUrls()
        val productImagesAdapter = ProductImagesAdapter(images.toMutableList())
        productImagesViewPager.adapter = productImagesAdapter
        view_pager_indicator.setupWithViewPager(productImagesViewPager, true)

        floatingWishlist.setOnClickListener {
            if (ADDED_TO_WISHLIST){
                ADDED_TO_WISHLIST = false
                floatingWishlist.supportBackgroundTintList = ColorStateList.valueOf(Color.parseColor("#9e9e9e"))
            }else{
                ADDED_TO_WISHLIST = true
                //floatingWishlist.supportBackgroundTintList = ColorStateList(resources.getColorStateList(R.color.colorPrimary))
            }
        }

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

        val id:Int = item.itemId
        if (id == R.id.main_search_icon){
            // todo search
            return true
        }else if (id == R.id.main_cart_icon){
            // todo cart
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search_cart_item, menu)
        return true
    }



}
