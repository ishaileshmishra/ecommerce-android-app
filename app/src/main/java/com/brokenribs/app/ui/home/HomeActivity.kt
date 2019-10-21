package com.brokenribs.app.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brokenribs.app.R
import com.brokenribs.app.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        //val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        setupViewPager()

    }

    private fun setupViewPager() {

        val adapter = HomeFragmentPagerAdapter(supportFragmentManager)
        val container = listOf("Fashion", "Home", "Mobile", "Electronics", "Beauty", "Appliances", "Toy & Baby", "More")
        for (item in container) {
            val itemFrame: ContainerFragment = ContainerFragment.newInstance(item)
            adapter.addFragment(itemFrame, item)
        }


        binding?.viewpager!!.adapter = adapter
        binding!!.tabs!!.setupWithViewPager(binding!!.viewpager)
    }

}
