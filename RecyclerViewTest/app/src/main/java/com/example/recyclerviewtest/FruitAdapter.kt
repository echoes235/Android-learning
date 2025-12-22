package com.example.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewtest.Fruit

class FruitAdapter(val fruitlist: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHoler>()
{
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view)
    {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder = ViewHoler(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int)
    {
        val fruit = fruitlist[position]
        holder.fruitImage.setImageResource(fruit.ImageId)
        holder.fruitName.text = fruit.name
        holder.itemView.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos == RecyclerView.NO_POSITION) return@setOnClickListener
            val fru = fruitlist[pos]
            Toast.makeText(
                holder.itemView.context,
                "You Clicked name ${fru.name}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        holder.fruitImage.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos == RecyclerView.NO_POSITION) return@setOnClickListener
            val fru = fruitlist[pos]
            Toast.makeText(
                holder.itemView.context,
                "You Clicked Image ${fru.name}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun getItemCount(): Int
    {
        return fruitlist.size
    }

}