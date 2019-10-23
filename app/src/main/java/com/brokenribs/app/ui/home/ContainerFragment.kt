package com.brokenribs.app.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import com.brokenribs.app.data.network.reponse.CuratedResponse
import com.brokenribs.app.data.network.reponse.PhotosModel
import com.brokenribs.app.util.ImagesUtils
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream


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


        val images: Array<String> = ImagesUtils.getImageUrls.getImageUrls()
        for (x in 1 until images.size){ products.add(Products("$page_section $x", images.get(x))) }
        val recyclerViewProducts = view?.findViewById<RecyclerView>(R.id.recycler_view)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(context, 5)
        } else {
            layoutManager = GridLayoutManager(context, 2)
        }

        recyclerViewProducts?.layoutManager = layoutManager
        products.shuffle()

        adapter = ContainerAdapter(products)
        adapter.replaceItems(products)
        recyclerViewProducts?.adapter = adapter

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

}

