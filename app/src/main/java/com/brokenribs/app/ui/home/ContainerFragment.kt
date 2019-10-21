package com.brokenribs.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import kotlin.math.log

class ContainerFragment : Fragment() {

    private lateinit var adapter: ContainerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = arguments?.getString(SECTION)
    }


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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.container_frament, container, false)

        val ARG_PAGE_SECTION = arguments?.getString(SECTION)

        val items = listOf(
            Products("Titen watch", "https://pbs.twimg.com/media/DlOPkMdXoAYwqRv.jpg"),
            Products("indistin Waber", "https://i.pinimg.com/474x/d4/f7/90/d4f790d479fcd9f79ba149efd36a1892.jpg"),
            Products("Content 01", "https://assets.vogue.com/photos/5c9e5a92991e7d2e51c59725/master/pass/00-promo-short-suits.jpg"),
            Products("Content 02", "https://assets.vogue.com/photos/5c9e5a93198a7c2e6e397a4f/master/w_1600%2Cc_limit/02-short-suits.jpg"),
            Products("Content 03", "hhttps://vickvanlian.com/wp-content/uploads/2019/03/short-and-sassy-hairstyles-super-best-pin-by-beth-hanson-on-hair-pinterest.jpg"),
            Products("Content 04", "https://i.pinimg.com/originals/80/15/bb/8015bb2af5dc6d480a3d14093eb212c9.jpg"),
            Products("Content 05", "https://i.pinimg.com/originals/18/da/81/18da817edb575645c96db8a858818ca7.jpg")
        )


        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        adapter = ContainerAdapter(items)
        adapter.replaceItems(items)
        recyclerView.adapter = adapter

        return view
    }



}
