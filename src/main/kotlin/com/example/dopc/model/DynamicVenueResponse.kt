package com.example.dopc.model

import kotlinx.serialization.Serializable


@Serializable
data class DynamicVenueResponse(
    val venue_raw: DynamicVenueRaw
)

@Serializable
data class DynamicVenueRaw(
    val delivery_specs: DeliverySpecs
)

@Serializable
data class DeliverySpecs(
    val order_minimum_no_surcharge: Int,
    val delivery_pricing: DeliveryPricing
)

@Serializable
data class DeliveryPricing(
    val base_price: Int,
    val distance_ranges: List<DistanceRange>
)

@Serializable
data class DistanceRange(
    val min: Int,
    val max: Int,
    val a: Int,
    val b: Double
)