plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id(Plugins.ktlint)

    id(Plugins.kotlinSpring)
    id(Plugins.springBoot) version Versions.springBoot
    id(Plugins.processes) version Versions.processes // for springDoc
    id(Plugins.springDoc) version Versions.springDoc
}

group = "com.inventale.project"

tasks.getByName<Jar>("bootJar") {
    archiveFileName.set("backend.jar")
}.finalizedBy("copyConfigsNearJar") // copy /config near build jars to be used in docker

dependencies {
    implementation(project(":backend"))

    implementation(Libs.springBootStarterActuator)
    implementation(Libs.springBootStarterWeb)
    implementation(Libs.springBootStarterAop)
    implementation(Libs.springdocOpenapi)
    implementation(Libs.springdocOpenapiKotlin)
    implementation(Libs.kotlinReflect)

    // put app logs in json format
    implementation(Libs.springBootStarterLogging)
    implementation(Libs.logstashLogbackEncoder)

    implementation(Libs.logger)
    implementation(Libs.jacksonKotlin)
    implementation(Libs.kotlinReflect)

    testImplementation(Libs.junit)
    testImplementation(Libs.junitEngine)
    testImplementation(Libs.junitParams)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.springBootStarterTest)
}

tasks.test {
    onlyIf {
        project.hasProperty("functionalTests")
    }
    useJUnitPlatform()
    // to use properties from /config in tests
    workingDir = rootDir
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
}
