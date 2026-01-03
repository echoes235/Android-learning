package com.example.sunnyweather.model

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for the Weather data model
 */
class WeatherTest {
    @Test
    fun weather_getTemperatureString_returnsFormattedString() {
        val weather = Weather(
            cityName = "Beijing",
            temperature = 25.7,
            description = "Sunny",
            humidity = 50,
            windSpeed = 3.5
        )
        
        assertEquals("25°C", weather.getTemperatureString())
    }
    
    @Test
    fun weather_creation_storesCorrectValues() {
        val weather = Weather(
            cityName = "Shanghai",
            temperature = 30.0,
            description = "Cloudy",
            humidity = 60,
            windSpeed = 5.0
        )
        
        assertEquals("Shanghai", weather.cityName)
        assertEquals(30.0, weather.temperature, 0.001)
        assertEquals("Cloudy", weather.description)
        assertEquals(60, weather.humidity)
        assertEquals(5.0, weather.windSpeed, 0.001)
    }
}
