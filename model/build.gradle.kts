plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id(Plugins.ktlint)
}

group = "com.inventale.project"

dependencies {
    implementation(Libs.logger)
    implementation(Libs.jacksonKotlin)
    implementation(Libs.kotlinReflect)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.kotlinJvmTarget
    }
}
