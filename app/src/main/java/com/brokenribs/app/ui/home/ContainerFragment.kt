package com.brokenribs.app.ui.home

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.brokenribs.app.R
import com.brokenribs.app.data.network.reponse.CuratedResponse
import com.brokenribs.app.data.network.reponse.PhotosModel
import com.brokenribs.app.ui.details.ProductImagesAdapter
import com.brokenribs.app.util.ImagesUtils
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.container_frament.*
import kotlinx.android.synthetic.main.container_frament.productImagesViewPager
import kotlinx.android.synthetic.main.container_frament.view_pager_indicator
import kotlinx.android.synthetic.main.products_img_layout.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream


class ContainerFragment : Fragment() {

    private lateinit var adapter: ContainerAdapter
    private lateinit var sectionAdapter: SectionAdapter
    private val productsRecommended: MutableList<Products> = ArrayList()
    private val productsChines: MutableList<Products> = ArrayList()
    private val productsVegs: MutableList<Products> = ArrayList()
    private val productsThai: MutableList<Products> = ArrayList()


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


    @RequiresApi(Build.VERSION_CODES.N)
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

        var sections: MutableList<String>? = null
        sections = ImagesUtils.getImages.getRecommendedUrl()

        sections.shuffle()
        val recyclerViewSection = view.findViewById<RecyclerView>(R.id.rv_top_sections)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSection?.layoutManager = layoutManager
        recyclerViewSection?.setHasFixedSize(true)
        sectionAdapter = SectionAdapter(sections)
        //ectionAdapter.replaceItems(sections)
        recyclerViewSection?.adapter = sectionAdapter

    }



    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadProducts(view: View, page_section: String){


        val images = ImagesUtils.getImages.offerImages()
        val productImagesAdapter = ProductImagesAdapter(images.toMutableList())
        val productImagesViewPager = view.findViewById<ViewPager>(R.id.productImagesViewPager)
        val view_pager_indicator = view.findViewById<TabLayout>(R.id.view_pager_indicator)
        productImagesViewPager.adapter = productImagesAdapter
        view_pager_indicator.setupWithViewPager(productImagesViewPager, true)


        // Section Recommended

        val imagesRecommended = ImagesUtils.getImages.getRecommendedUrl()
        imagesRecommended.shuffled()
        for (x in 1 until imagesRecommended.size){ productsRecommended.add(Products("Recommended $x", imagesRecommended.get(x))) }
        val recyclerViewProducts = view.findViewById<RecyclerView>(R.id.recycler_view_section)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProducts?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsRecommended)
        //adapter.replaceItems(products)
        recyclerViewProducts?.adapter = adapter


        //Section Vegetarian

        val imagesVeg = ImagesUtils.getImages.getVegs()
        imagesVeg.shuffled()
        for (x in 1 until imagesVeg.size){ productsVegs.add(Products("Veg food $x", imagesVeg.get(x))) }
        val vegRecyclerView = view.findViewById<RecyclerView>(R.id.vegRecyclerView)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        vegRecyclerView?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsVegs)
        //adapter.replaceItems(products)
        vegRecyclerView?.adapter = adapter


        // Section Vegetarian

        val imagesChinese = ImagesUtils.getImages.getChinese()
        imagesChinese.shuffled()
        for (x in 1 until imagesChinese.size){ productsChines.add(Products("Chinese $x", imagesChinese.get(x))) }
        val recyclerViewChinies = view.findViewById<RecyclerView>(R.id.recyclerViewChinies)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewChinies?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsChines)
        //adapter.replaceItems(products)
        recyclerViewChinies?.adapter = adapter



        // Section Thai

        val imagesThai = ImagesUtils.getImages.getThais()
        imagesThai.shuffled()
        for (x in 1 until imagesThai.size){ productsThai.add(Products("Thai $x", imagesThai.get(x))) }
        val recyclerViewThai = view.findViewById<RecyclerView>(R.id.recyclerViewThai)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewThai?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsThai)
        //adapter.replaceItems(products)
        recyclerViewThai?.adapter = adapter

    }



    private fun readJSONFromAsset(): String? {
        var json: String? = null
        try {

            val  inputStream: InputStream = context!!.assets.open("response.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }



    fun fetchDataFromServer(){
        /*        Coroutines.main {
            try {

                val appDatabse = AppDatabase(requireContext())
                val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
                val brokenAPI = BrokenAPI(networkConnectionInterceptor);
                val repository = UserRepository(brokenAPI, appDatabse)

                val loginResponse: CuratedResponse = repository.photos(15, 1)
                Log.e("loginResponse: ", loginResponse.toString())

            }catch (e: APIException){
                e.printStackTrace()
            }

        }*/


//        val obj = JSONObject(readJSONFromAsset())
//        var  productlist = mutableListOf<PhotosModel>()
//        if (obj !=null){
//
//            val page = obj.opt("page")
//            val perPage = obj.opt("per_page")
//            val photos: JSONArray = obj.getJSONArray("photos")
//
//            for (i in 0.. photos.length())
//            {
//
//                val photosObj:JSONObject=photos.getJSONObject(i)
//                var productModel = PhotosModel(
//                    photosObj.optInt("id"),
//                    photosObj.optInt("width"),
//                    photosObj.optInt("height"),
//                    photosObj.optString("url"),
//                    photosObj.optString("photographer"),
//                    photosObj.optString("photographer_url"),
//                    photosObj.optString("photographer_id"),
//                    photosObj.opt("src"),
//                    photosObj.optBoolean("liked")
//                )
//                productlist.add(productModel)
//            }
//
//        }
//        Log.e("productModel", productlist.toString())




    }

}

