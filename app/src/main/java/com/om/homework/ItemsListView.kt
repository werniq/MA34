package com.om.homework

import android.os.Bundle
import android.content.Context
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemsListView : AppCompatActivity() {

    private val fruitList = arrayOf("Apple", "Banana", "Orange", "Lemon")
    private val fruitsImages = intArrayOf(R.drawable.apple, R.drawable.banana, R.drawable.orange, R.drawable.lemon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_items)
        Toast.makeText(this@ItemsListView, "On create", Toast.LENGTH_SHORT).show()

        val mListView = findViewById<ListView>(R.id.customListView)
        val adapter = CustomAdapter(this, fruitList, fruitsImages)

        mListView.adapter = adapter
    }
}
