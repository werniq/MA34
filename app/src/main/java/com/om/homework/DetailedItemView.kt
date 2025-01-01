package com.om.homework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
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

        val backButton = findViewById<Button>(R.id.button);
        backButton.setOnClickListener {
            val intent = Intent(this, ItemsListView::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        };
    }
}
