package com.inventale.project

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import java.net.URL

private val logger = KotlinLogging.logger {}

// list of used spring profiles. Las profiles override properties from previous ones
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = ["test"])
class HelloWorldAppAcceptanceIT {
    // bind the above RANDOM_PORT
    @LocalServerPort
    protected var port = 0

    // to check Http endpoint
    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Test
    fun request() {
        sendRequest()
            .also {
                Assertions.assertEquals(HttpStatus.OK, it.statusCode)
            }
            .also {
                Assertions.assertNotNull(it.body)
                Assertions.assertFalse(it.body!!.isEmpty())
            }
    }

    private fun sendRequest(): ResponseEntity<String> = restTemplate
        .exchange(
            URL(String.format("$BASE_URL/hello", port)).toString(),
            HttpMethod.GET,
            HttpEntity(
                "",
                HttpHeaders().apply {
                    contentType = MediaType.APPLICATION_JSON
                }
            ),
            String::class.java
        )
        .also {
            logger.debug { "Response: ${it.statusCode}: ${it.body}" }
        }

    companion object {
        const val BASE_URL = "http://localhost:%s"
    }
}
