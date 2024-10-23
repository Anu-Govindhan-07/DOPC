package com.example.dopc.model


import kotlinx.serialization.Serializable

@Serializable
data class StaticVenueResponse(
    val venue_raw: VenueRaw
)

@Serializable
data class VenueRaw(
    val location: Location
)

@Serializable
data class Location(
    val coordinates: List<Double>  // Longitude and Latitude array
)