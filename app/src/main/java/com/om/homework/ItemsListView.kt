package com.om.homework

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemsListView : AppCompatActivity() {

    private val fruitList = arrayOf("Apple", "Banana", "Orange", "Lemon")
    private val fruitsImages = intArrayOf(R.drawable.apple, R.drawable.banana, R.drawable.orange, R.drawable.lemon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_items)

        val mListView = findViewById<ListView>(R.id.customListView)
        val adapter = CustomAdapter(this, fruitList, fruitsImages)

        mListView.adapter = adapter

        mListView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@ItemsListView, "Activating fruit $position", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", fruitList[position])
            intent.putExtra("image", fruitsImages[position])
            intent.putExtra("description", "${fruitList[position]} is a healthy fruit.") // Example description
            startActivity(intent)
            Toast.makeText(this@ItemsListView, "Finished activating fruit", Toast.LENGTH_SHORT).show()
        }
    }
}
