import com.example.dopc.model.*
import com.example.dopc.service.DeliveryOrderPriceServiceImpl
import com.example.dopc.service.HomeAssignmentApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class DeliveryOrderPriceServiceTest {

    private val mockApiService = mockk<HomeAssignmentApiService>()
    private val deliveryService = DeliveryOrderPriceServiceImpl(mockApiService)

    @Test
    fun `should calculate total price with delivery fee and surcharge`() = runBlocking {
        // Mock static and dynamic API responses
        val mockStaticResponse = StaticVenueResponse(VenueRaw(Location(listOf(24.938379, 60.169856))))
        val mockDynamicResponse = DynamicVenueResponse(
            DynamicVenueRaw(DeliverySpecs(1000, DeliveryPricing(500, listOf(DistanceRange(0, 5000, 200, 1.5)))))
        )

        coEvery { mockApiService.getStaticData("test_slug") } returns mockStaticResponse
        coEvery { mockApiService.getDynamicData("test_slug") } returns mockDynamicResponse

        val result = deliveryService.calculatePrice("test_slug", 800, 60.205490, 24.655899)

        // Assertions for total price, surcharge, and delivery fee
        assertEquals(1900, result.total_price)
        assertEquals(200, result.delivery.fee)
        assertEquals(1000, result.small_order_surcharge)
    }
}
