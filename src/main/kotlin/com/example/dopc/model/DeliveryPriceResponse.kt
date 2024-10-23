package com.example.dopc.model

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryPriceResponse(
    val total_price: Int,  // Change to Int
    val small_order_surcharge: Int,
    val cart_value: Int,
    val delivery: DeliveryInfo
) {
    @Serializable
    data class DeliveryInfo(
        val fee: Int,  // Change to Int
        val distance: Int
    )
}