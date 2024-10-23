package com.example.dopc.service


import com.example.dopc.model.DeliveryPriceResponse

interface DeliveryOrderPriceServiceInterface {
    suspend fun calculatePrice(venueSlug: String, cartValue: Int, userLat: Double, userLon: Double): DeliveryPriceResponse
}