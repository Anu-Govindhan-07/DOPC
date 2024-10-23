package com.example.dopc
import configureRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class DeliveryOrderPriceControllerTest {

    @Test
    fun `should return bad request for missing parameters`() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/api/v1/delivery-order-price").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals("Missing venue_slug", response.content)
            }
        }
    }

}
