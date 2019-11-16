package com.brokenribs.app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brokenribs.app.R
import com.brokenribs.app.util.snackbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.section_item.view.*

class SectionAdapter (private var items: MutableList<String> ) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.section_item, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = items.get(position)
        holder.tvSection.text = "section $position"

        Glide.with(holder.itemView.context)
            .load(item).apply(RequestOptions.circleCropTransform())
            .into(holder.ivSection)

        //holder.productIcon.setImageDrawable(R.drawable.ic_shopping_basket_icon)//(item.uriSource)
        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, item.title, Toast.LENGTH_LONG).show()
            holder.itemView.snackbar("${item} tapped")
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivSection = view.ivSection
        val tvSection = view.tvSection
    }

    fun replaceItems(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }
}



