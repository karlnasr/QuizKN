package com.example.quizkn.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizkn.R
import kotlinx.android.synthetic.main.item_custom.view.*

class ItemAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_custom,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = items.get(position)
        if (position == 0) {
            holder.tvItem.text = "Music"
            holder.itemImage.setImageResource(R.drawable.ic_music)
        }
        if (position == 1) {
            holder.tvItem.text = "Sports"
            holder.itemImage.setImageResource(R.drawable.ic_soccer)
        }
        if(position == 2){
            holder.tvItem.text="Education"
            holder.itemImage.setImageResource(R.drawable.ic_school)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.tv_item_name
        val cardviewItem = view.card_view_tem
        val itemImage = view.item_image
    }
}