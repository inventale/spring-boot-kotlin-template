package com.inventale.project

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HelloWorldServiceTest {
    @ParameterizedTest
    @MethodSource("provideData")
    fun testHelloWorldService(target: String) {

        val helloWorldMessageProvider = mock<HelloWorldMessageProvider> {
            on { provideMessage() } doReturn target
        }
        val helloWorldService = HelloWorldService(helloWorldMessageProvider)
        val actualResult = helloWorldService.provideHelloWorld()
        Assertions.assertEquals(HelloWorldResult(target), actualResult)
    }

    companion object {
        @JvmStatic
        private fun provideData() = listOf(
            Arguments.of("test1"),
            Arguments.of("test2")
        )
    }
}
