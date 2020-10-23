package com.inventale.project.controller

import com.inventale.project.HelloWorldResult
import com.inventale.project.HelloWorldService
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@Timed
class HelloWorldController @Autowired constructor(
    private val helloWorldService: HelloWorldService
) {
    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    fun helloWorld(): HelloWorldResult = helloWorldService
        .provideHelloWorld()
        .also {
            logger.debug { "Response to /hello: $it" }
        }
}
