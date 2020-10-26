package com.inventale.project

import com.inventale.project.metrics.PrometheusMetrics
import io.micrometer.core.annotation.Timed

open class DefaultHelloWorldMessageProvider(
    private val helloWorldTarget: String
) : HelloWorldMessageProvider {
    // extra tags need to split time series to separate implementations:
    // default and dev message length will be stored in separate time series
    @Timed(value = PrometheusMetrics.HELLO_WORLD_PROVIDER_TIMED, extraTags = ["implementation", "default"])
    override fun provideMessage(): String = "Hello world, $helloWorldTarget"
}
