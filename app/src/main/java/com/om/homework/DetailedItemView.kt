package com.om.homework

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_item_view)

        val itemImage = findViewById<ImageView>(R.id.itemImage)
        val itemName = findViewById<TextView>(R.id.itemName)
        val itemDescription = findViewById<TextView>(R.id.itemDescription)

        val name = intent.getStringExtra("name")
        val image = intent.getIntExtra("image", 0)
        val description = intent.getStringExtra("description")

        // Set data to the views
        itemName.text = name
        itemImage.setImageResource(image)
        itemDescription.text = description
    }
}
