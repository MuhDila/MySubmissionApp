package com.muhdila.mysubmissionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSingerAdapter(private val listSingers: ArrayList<Singer>) : RecyclerView.Adapter<ListSingerAdapter.ListViewHolder>() {

    // Declare a callback interface for item click events
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Set the item click callback from outside the adapter
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Inflate the item view layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_singer, parent, false)
        return ListViewHolder(view)
    }

    // Bind data to the ViewHolder and set click listener
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Get the data for the current position
        val (name, description, _, _, _, photo) = listSingers[position]

        // Set data to the ViewHolder's views
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        // Set click listener on the entire item view
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listSingers[holder.adapterPosition]) }
    }

    // Return the number of items in the data list
    override fun getItemCount(): Int = listSingers.size

    // ViewHolder class to hold the views for each item
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    // Callback interface for item click events
    interface OnItemClickCallback {
        fun onItemClicked(data: Singer)
    }
}