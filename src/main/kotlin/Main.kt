import com.example.dopc.api.v1.deliveryOrderPriceController
import com.example.dopc.io.HttpClientFactory
import com.example.dopc.service.DeliveryOrderPriceServiceImpl
import com.example.dopc.service.HomeAssignmentApiService

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        // Call the function that configures the Ktor server
        configureRouting()
    }.start(wait = true)
}

fun Application.configureRouting() {
    // Install content negotiation for JSON
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true // Allows ignoring unknown fields in incoming JSON
        })
    }

    // Create the necessary services
    val client = HttpClientFactory.create()
    val homeAssignmentApiService = HomeAssignmentApiService(client)
    val deliveryService = DeliveryOrderPriceServiceImpl(homeAssignmentApiService)

    // Define the routes
    routing {
        deliveryOrderPriceController(deliveryService)
    }
}