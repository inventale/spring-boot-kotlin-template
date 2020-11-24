object Versions {
    const val kotlin = "1.4.10"
    const val kotlinJvmTarget = "11"

    //Plugins
    const val versionsPlugin = "0.33.0"
    const val owaspPlugin = "6.0.2"
    const val taskTreePlugin = "1.5"
    const val ktlintPlugin = "9.4.1"
    const val springDoc = "1.3.0"
    const val processes = "0.5.0"

    //Libs
    const val springBoot = "2.3.4.RELEASE"
    const val micrometer = "1.5.5"
    const val jacksonKotlin = "2.11.1"
    const val loggerVersion = "2.0.3"
    const val springdocOpenapi = "1.4.8"

    // logs
    const val logbackClassic = "1.2.3"
    const val logstashLogbackEncoder = "6.3"

    // Libs for testing
    const val junit = "5.7.0"
    const val mockitoKotlin = "1.6.0"
}

object Plugins {
    const val springBoot = "org.springframework.boot"
    const val versions = "com.github.ben-manes.versions"
    const val owasp = "org.owasp.dependencycheck"
    const val taskTree = "com.dorongold.task-tree"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val kotlinSpring = "org.jetbrains.kotlin.plugin.spring"
    const val springDoc = "org.springdoc.openapi-gradle-plugin"
    const val processes = "com.github.johnrengelman.processes"
}

object Libs {
    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val logger = "io.github.microutils:kotlin-logging:${Versions.loggerVersion}"

    const val springBootStarter = "org.springframework.boot:spring-boot-starter:${Versions.springBoot}"
    const val springBootStarterActuator = "org.springframework.boot:spring-boot-starter-actuator:${Versions.springBoot}"
    const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web:${Versions.springBoot}"
    const val springBootStarterAop = "org.springframework.boot:spring-boot-starter-aop:${Versions.springBoot}"
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}"
    const val springdocOpenapi = "org.springdoc:springdoc-openapi-ui:${Versions.springdocOpenapi}"
    const val springdocOpenapiKotlin = "org.springdoc:springdoc-openapi-kotlin:${Versions.springdocOpenapi}"

    // metrics
    const val micrometerPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometer}"
    const val micrometerCore = "io.micrometer:micrometer-core:${Versions.micrometer}"

    // logs
    const val logstashLogbackEncoder = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogbackEncoder}"
    const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logbackClassic}"
    const val logbackCore = "ch.qos.logback:logback-core:${Versions.logbackClassic}"
    const val logbackAccess = "ch.qos.logback:logback-access:${Versions.logbackClassic}"

    // Test libraries
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
}
