package com.example.sunnyweather

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnyweather.model.Weather

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Create sample weather data
        val weather = Weather(
            cityName = "Beijing",
            temperature = 20.0,
            description = "Sunny",
            humidity = 45,
            windSpeed = 3.5
        )
        
        // Display weather data in the UI
        val cityName = findViewById<TextView>(R.id.cityName)
        val temperature = findViewById<TextView>(R.id.temperature)
        val weatherDescription = findViewById<TextView>(R.id.weatherDescription)
        
        cityName.text = weather.cityName
        temperature.text = weather.getTemperatureString()
        weatherDescription.text = weather.description
    }
}
