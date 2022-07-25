package com.example.marsproperty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecyclerViewAdapter(val context: Context,val item:List<MarsProperty>): RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>() {

    private lateinit var mlistener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mlistener = listener
    }

    class viewHolder(itemView: View, listener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.marsImage)
        var type = itemView.findViewById<TextView>(R.id.property_type)
        var Rent = itemView.findViewById<TextView>(R.id.property_rent)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout,parent,false)
        return viewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val marsProperty:MarsProperty = item[position]
        holder.Rent.text = "price:-${marsProperty.price.toString()}"
        holder.type.text = "Type:-${marsProperty.type}"
        Glide.with(context)
            .load(marsProperty.img_src)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }

}