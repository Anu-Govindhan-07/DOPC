import com.example.dopc.util.DistanceCalculator
import kotlin.test.Test
import kotlin.test.assertEquals

class DistanceCalculatorTest {

    @Test
    fun `should calculate correct distance between two locations`() {
        val userLat = 60.169856 // Helsinki
        val userLon = 24.938379 // Helsinki
        val venueLat = 60.205490 // Espoo
        val venueLon = 24.655899 // Espoo

        val distance = DistanceCalculator.calculateDistance(userLat, userLon, venueLat, venueLon)

        // Approximate distance between Helsinki and Espoo is around 20,000 meters.
        assertEquals(20000, distance, "Distance should be around 20,000 meters")
    }
}
