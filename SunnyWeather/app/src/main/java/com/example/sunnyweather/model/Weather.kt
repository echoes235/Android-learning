package com.example.sunnyweather.model

/**
 * Weather data class representing weather information for a location
 */
data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val humidity: Int,
    val windSpeed: Double
) {
    /**
     * Get temperature in Celsius as a formatted string
     */
    fun getTemperatureString(): String {
        return "${temperature.toInt()}°C"
    }
}
