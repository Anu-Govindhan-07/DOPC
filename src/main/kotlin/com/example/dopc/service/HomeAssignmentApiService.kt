package com.example.dopc.service

import com.example.dopc.model.DynamicVenueResponse
import com.example.dopc.model.StaticVenueResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class HomeAssignmentApiService(private val client: HttpClient = HttpClient()) {

    suspend fun getStaticData(venueSlug: String): StaticVenueResponse {
        return client.get("https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueSlug/static").body()
    }

    suspend fun getDynamicData(venueSlug: String): DynamicVenueResponse {
        return client.get("https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueSlug/dynamic").body()
    }
}