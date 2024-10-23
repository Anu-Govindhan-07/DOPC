plugins {
    kotlin("jvm") version "1.9.10"  // Use the latest stable Kotlin version
    kotlin("plugin.serialization") version "1.9.10"
    application
}

repositories {
    mavenCentral()  // Ensure you're using Maven Central
}

dependencies {
    // Ktor server dependencies
    // Logback for SLF4J
    implementation("ch.qos.logback:logback-classic:1.2.11")

    implementation("io.ktor:ktor-server-core:2.0.3")           // Core Ktor server
    implementation("io.ktor:ktor-server-netty:2.0.3")          // Netty server engine
    // implementation("io.ktor:ktor-server-content-negotiation:2.0.3") // Content negotiation
    // implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3") // Serialization for JSON
// Ktor JSON content negotiation
    implementation("io.ktor:ktor-server-content-negotiation:2.0.3")

    // Ktor JSON serialization using Kotlinx
    // Ktor client dependencies
    implementation("io.ktor:ktor-client-core:2.0.3")            // Core Ktor client
    implementation("io.ktor:ktor-client-cio:2.0.3")             // CIO engine for Ktor client
    implementation("io.ktor:ktor-client-content-negotiation:2.0.3") // Client-side content negotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3") // JSON serialization for the client

    // Kotlinx Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // Testing dependencies
    testImplementation(kotlin("test"))
    // Additional testing libraries if needed
    testImplementation("io.mockk:mockk:1.12.0")  // Mocking library
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")  // Coroutines testing
    testImplementation("io.ktor:ktor-server-test-host:2.0.3")  // Ktor test host for integration testing
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.10")
    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.31")
    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.13.2")
    // Other dependencies
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.ktor:ktor-server-test-host:2.0.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.2")
}

application {
    mainClass.set("com.example.dopc.MainKt")  // Replace this with your main class if different
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}


tasks.test {
    useJUnitPlatform() // Use JUnit platform for running tests
}