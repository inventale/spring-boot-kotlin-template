package com.inventale.project

import com.inventale.project.metrics.PrometheusMetrics
import io.micrometer.core.instrument.Metrics
import java.util.concurrent.atomic.AtomicInteger

class HelloWorldService(private val helloWorldMessageProvider: HelloWorldMessageProvider) {

    fun provideHelloWorld(): HelloWorldResult = helloWorldMessageProvider
        .provideMessage()
        .also {
            // increment counter
            Metrics.counter(PrometheusMetrics.HELLO_WORLD_COUNT).increment()
        }
        .also {
            // set gauge
            LAST_MESSAGE_LETTERS.set(it.length)
        }
        .let {
            HelloWorldResult(it)
        }

    companion object {
        private val LAST_MESSAGE_LETTERS = AtomicInteger(0)
    }

    init {
        // link AtomicInteger instance with metric. Every time the AtomicInteger is updated, metric will be changed.
        Metrics.gauge(PrometheusMetrics.HELLO_WORLD_LAST_MESSAGE_LETTERS_NUMBER, LAST_MESSAGE_LETTERS)
    }
}
