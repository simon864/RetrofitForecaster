package com.bignerdranch.android.retrofitforecaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val weatherData = RetrofitService.create().getWeatherForecast("Shklov", "e95a93c258eb63a193e07e680c12daab", "metric")
            withContext(Dispatchers.Main) {
                adapter.submitList(weatherData.list)
            }
        }
    }
}