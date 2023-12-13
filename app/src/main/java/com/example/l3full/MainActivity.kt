package com.example.l3full

import Student
import StudentsAdapter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), StudentsAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLoadData: Button = findViewById(R.id.buttonLoadData)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonLoadData.setOnClickListener {
            loadDataFromJson()
        }
    }

    private fun loadDataFromJson() {
        try {
            val inputStream: InputStream = assets.open("jj.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val students = Gson().fromJson<List<Student>>(jsonString, object : TypeToken<List<Student>>() {}.type)
            adapter = StudentsAdapter(students, this)
            recyclerView.adapter = adapter
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    override fun onItemClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HelloFragment())
            .addToBackStack(null)
            .commit()
    }
}
