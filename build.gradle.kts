plugins {
    kotlin("jvm")
    `maven-publish`
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint)
}

repositories {
    mavenCentral()
}

ktlint {
    version.set("1.5.0")
    android.set(false)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    filter {
        exclude("**/build/**")
    }
}
