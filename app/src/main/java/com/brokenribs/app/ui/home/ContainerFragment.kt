package com.brokenribs.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import com.brokenribs.app.util.ImagesUtils

class ContainerFragment : Fragment() {

    private lateinit var adapter: ContainerAdapter
    private lateinit var sectionAdapter: SectionAdapter

    private val products: MutableList<Products> = ArrayList()


    companion object {

        val SECTION: String = "section"

        @JvmStatic
        fun newInstance(name: String): ContainerFragment {
            val args = Bundle()
            args.putString(SECTION, name)
            val fragment = ContainerFragment()
            fragment.arguments = args
            return fragment
        }
    }


    private lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.container_frament, container, false)
        val page_section = arguments?.getString(SECTION)

        loadTopSections(view)
        if (page_section != null) {
            loadProducts(view, page_section)
        }

        return view
    }


    private fun loadTopSections(view: View){

        val sections: Array<String> = ImagesUtils.offers.getOffersUrls()
        val recyclerViewSection = view?.findViewById<RecyclerView>(R.id.rv_top_sections)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSection?.layoutManager = layoutManager
        recyclerViewSection?.setHasFixedSize(true)

        sectionAdapter = SectionAdapter(sections)
        sectionAdapter.replaceItems(sections)
        recyclerViewSection?.adapter = sectionAdapter

    }



    private fun loadProducts(view: View, page_section: String){

        val images: Array<String> = ImagesUtils.getImageUrls.getImageUrls()
        for (x in 1 until images.size){ products.add(Products("$page_section $x", images.get(x))) }
        val recyclerViewProducts = view?.findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = GridLayoutManager(context, 2)
        recyclerViewProducts?.layoutManager = layoutManager

        //resuffle list item
        products.shuffle()

        adapter = ContainerAdapter(products)
        adapter.replaceItems(products)
        recyclerViewProducts?.adapter = adapter

    }

}

