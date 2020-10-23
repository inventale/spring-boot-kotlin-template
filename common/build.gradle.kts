plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id(Plugins.ktlint)
}

group = "com.inventale.project"

dependencies {
    api(project(":model"))

    implementation(Libs.logger)
    implementation(Libs.jacksonKotlin)
    implementation(Libs.kotlinReflect)

    testImplementation(Libs.junit)
    testImplementation(Libs.junitEngine)
    testImplementation(Libs.junitParams)
    testImplementation(Libs.mockitoKotlin)
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
}
