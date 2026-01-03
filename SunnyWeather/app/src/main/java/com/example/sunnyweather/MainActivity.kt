package com.example.sunnyweather

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val cityName = findViewById<TextView>(R.id.cityName)
        val temperature = findViewById<TextView>(R.id.temperature)
        val weatherDescription = findViewById<TextView>(R.id.weatherDescription)
        
        // Display sample weather data
        cityName.text = "Beijing"
        temperature.text = "20°C"
        weatherDescription.text = "Sunny"
    }
}
