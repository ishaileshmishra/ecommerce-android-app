package com.brokenribs.app.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import com.brokenribs.app.ui.details.DetailActivity
import com.brokenribs.app.util.snackbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.product_item_layout.view.*

class ContainerAdapter (private var items: List<Products> ) : RecyclerView.Adapter<ContainerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Products = items.get(position)
        holder.chapterName.text = item.title

        Glide.with(holder.itemView.context)//.asGif()
            .load(item.uriSource) //.apply(RequestOptions.circleCropTransform())
            .into(holder.productIcon)

        holder.itemView.setOnClickListener {

            holder.itemView.snackbar("${item.title} tapped")
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("uriSource", item.uriSource)
            holder.itemView.context.startActivity(intent)
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



