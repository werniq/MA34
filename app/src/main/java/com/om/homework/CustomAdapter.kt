package com.om.homework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(
    private val context: Context,
    private val fruitList: Array<String>,
    private val images: IntArray
) : BaseAdapter() {

    override fun getCount(): Int {
        return fruitList.size
    }

    override fun getItem(position: Int): Any {
        return fruitList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.items_list_view, parent, false)
        val fruitName = view.findViewById<TextView>(R.id.item_text)
        val fruitImage = view.findViewById<ImageView>(R.id.item_icon)

        fruitName.text = fruitList[position]
        fruitImage.setImageResource(images[position])

        return view
    }
}
