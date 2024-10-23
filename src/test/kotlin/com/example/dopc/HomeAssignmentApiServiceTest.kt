import com.example.dopc.model.*
import com.example.dopc.service.HomeAssignmentApiService
import io.ktor.client.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeAssignmentApiServiceTest {

    private val mockClient = mockk<HttpClient>()
    private val apiService = HomeAssignmentApiService(mockClient)

    @Test
    fun `should return static venue data`() = runBlocking {
        // Mock the static response
        val mockStaticResponse = StaticVenueResponse(VenueRaw(Location(listOf(24.938379, 60.169856))))
        coEvery { apiService.getStaticData("test_slug") } returns mockStaticResponse

        val response = apiService.getStaticData("test_slug")

        // Assert that the response matches the mock data
        assertEquals(60.169856, response.venue_raw.location.coordinates[1])
    }

    @Test
    fun `should return dynamic venue data`() = runBlocking {
        // Mock the dynamic response
        val mockDynamicResponse = DynamicVenueResponse(DynamicVenueRaw(DeliverySpecs(1000, DeliveryPricing(500, listOf(DistanceRange(0, 5000, 200, 1.5))))))
        coEvery { apiService.getDynamicData("test_slug") } returns mockDynamicResponse

        val response = apiService.getDynamicData("test_slug")

        // Assert that the dynamic response matches the mock data
        assertEquals(1000, response.venue_raw.delivery_specs.order_minimum_no_surcharge)
    }
}
