package com.example.dopc.util

object DistanceCalculator {
    fun calculateDistance(userLat: Double, userLon: Double, venueLat: Double, venueLon: Double): Int {
        val R = 6371e3 // Earth's radius in meters
        val latDistance = Math.toRadians(venueLat - userLat)
        val lonDistance = Math.toRadians(venueLon - userLon)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return (R * c).toInt()
    }
}