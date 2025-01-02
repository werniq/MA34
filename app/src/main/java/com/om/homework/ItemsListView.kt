package com.om.homework;

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import org.json.JSONArray
import kotlin.math.log

class ItemsListView : AppCompatActivity() {

    private var fruitList = arrayOf<String>()
    private val fruitsImages = intArrayOf(R.drawable.apple, R.drawable.banana, R.drawable.orange, R.drawable.lemon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_items)

        val mListView = findViewById<ListView>(R.id.customListView)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = URL("https://run.mocky.io/v3/af43a545-5504-4cdd-a17b-fa781f8e09c7").readText()
                val parsedList = parseFruitList(response)
                withContext(Dispatchers.Main) {
                    fruitList = parsedList
                    val adapter = CustomAdapter(this@ItemsListView, fruitList, fruitsImages)
                    mListView.adapter = adapter
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ItemsListView, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    throw e;
                }
            }
        }

        mListView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@ItemsListView, "Activating fruit $position", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", fruitList[position])
            intent.putExtra("image", fruitsImages[position % fruitsImages.size])
            intent.putExtra("description", "${fruitList[position]} is a healthy fruit.")
            startActivity(intent)
            Toast.makeText(this@ItemsListView, "Finished activating fruit", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseFruitList(response: String): Array<String> {
        return try {
            val jsonArray = JSONArray(response)
            Array(jsonArray.length()) { index -> jsonArray.getString(index) }
        } catch (e: Exception) {
            arrayOf("Apple", "Banana", "Orange", "Lemon")
        }
    }
}
