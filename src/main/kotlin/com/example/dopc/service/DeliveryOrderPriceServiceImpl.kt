package com.example.dopc.service

import com.example.dopc.model.DeliveryPriceResponse
import com.example.dopc.model.DynamicVenueResponse
import com.example.dopc.util.DistanceCalculator
import kotlin.math.max
import kotlin.math.roundToInt

class DeliveryOrderPriceServiceImpl(
    private val homeAssignmentApiService: HomeAssignmentApiService
) : DeliveryOrderPriceServiceInterface {

    override suspend fun calculatePrice(venueSlug: String, cartValue: Int, userLat: Double, userLon: Double): DeliveryPriceResponse {
        val staticData = homeAssignmentApiService.getStaticData(venueSlug)
        val dynamicData = homeAssignmentApiService.getDynamicData(venueSlug)
        val venueLat = staticData.venue_raw.location.coordinates[1]
        val venueLon = staticData.venue_raw.location.coordinates[0]
        val distance = DistanceCalculator.calculateDistance(userLat, userLon, venueLat, venueLon)

        val deliveryFee = calculateDeliveryFee(dynamicData, distance)
        val smallOrderSurcharge = calculateSmallOrderSurcharge(dynamicData, cartValue)
        val totalPrice = cartValue + deliveryFee + smallOrderSurcharge

        return DeliveryPriceResponse(
            total_price = totalPrice.roundToInt(),
            small_order_surcharge = smallOrderSurcharge,
            cart_value = cartValue,
            delivery = DeliveryPriceResponse.DeliveryInfo(
                fee = deliveryFee.roundToInt(),
                distance = distance
            )
        )
    }

    private fun calculateDeliveryFee(dynamicData: DynamicVenueResponse, distance: Int): Double {
        val distanceRange = dynamicData.venue_raw.delivery_specs.delivery_pricing.distance_ranges.find {
            distance in it.min until it.max || it.max == 0
        } ?: throw IllegalArgumentException("Delivery not possible for this distance")

        return dynamicData.venue_raw.delivery_specs.delivery_pricing.base_price +
                distanceRange.a + (distanceRange.b * distance / 10)
    }

    private fun calculateSmallOrderSurcharge(dynamicData: DynamicVenueResponse, cartValue: Int): Int {
        val minimumOrder = dynamicData.venue_raw.delivery_specs.order_minimum_no_surcharge
        return max(0, minimumOrder - cartValue)
    }
}