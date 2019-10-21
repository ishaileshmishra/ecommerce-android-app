package com.brokenribs.app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import com.brokenribs.app.util.setImageDrawable
import com.brokenribs.app.util.snackbar
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.product_item_layout.view.*

class ContainerAdapter (private var items: List<Products> ) : RecyclerView.Adapter<ContainerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Products = items.get(position)
        holder.chapterName.text = item.title

        Glide.with(holder.itemView.context)//.asGif()
            .load(item.uriSource)
            .into(holder.productIcon)

        //holder.productIcon.setImageDrawable(R.drawable.ic_shopping_basket_icon)//(item.uriSource)
        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, item.title, Toast.LENGTH_LONG).show()
            holder.itemView.snackbar("${item.title} tapped")
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chapterName = view.tvChapterName
        val productIcon = view.productIcon
    }

    fun replaceItems(items: List<Products>) {
        this.items = items
        notifyDataSetChanged()
    }
}



