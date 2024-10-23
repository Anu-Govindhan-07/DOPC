package com.example.dopc.api.v1

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import com.example.dopc.service.DeliveryOrderPriceServiceInterface

fun Route.deliveryOrderPriceController(deliveryService: DeliveryOrderPriceServiceInterface) {
    get("/api/v1/delivery-order-price") {

        val venueSlug = call.parameters["venue_slug"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing venue_slug")
        val cartValue = call.parameters["cart_value"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid cart_value")
        val userLat = call.parameters["user_lat"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid user_lat")
        val userLon = call.parameters["user_lon"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid user_lon")

        try {
            val result = deliveryService.calculatePrice(venueSlug, cartValue, userLat, userLon)
            call.respond(HttpStatusCode.OK, result)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid Request")
        }
    }
    get("/api/hai"){
        print(
            "hai testing"
        )
    }
}