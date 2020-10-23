package com.inventale.project

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class HelloWorldConfig {
    @Bean
    fun helloWorldService(
        helloWorldMessageProvider: HelloWorldMessageProvider
    ): HelloWorldService {
        return HelloWorldService(helloWorldMessageProvider)
    }

    @Profile(ProfileNames.DEV)
    @Bean
    fun devHelloWorldMessageProvider(
        @Value(value = "\${backend.hello-world-target}") helloWorldTarget: String
    ): HelloWorldMessageProvider {
        return DevHelloWorldMessageProvider(helloWorldTarget)
    }

    @Profile("!" + ProfileNames.DEV)
    @Bean
    fun defaultHelloWorldMessageProvider(
        @Value(value = "\${backend.hello-world-target}") helloWorldTarget: String
    ): HelloWorldMessageProvider {
        return DefaultHelloWorldMessageProvider(helloWorldTarget)
    }

    // Enable @Timed annotation aspects
    @Bean
    fun timedAspect(registry: MeterRegistry): TimedAspect {
        return TimedAspect(registry)
    }
}
