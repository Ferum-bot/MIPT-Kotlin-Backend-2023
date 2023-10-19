import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.9.10"
    application
}

group = "com.mipt.kotlin.ktor.practice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    val ktorVersion = "2.3.5"
    val koinVersion = "3.5.0"

    testImplementation(kotlin("test"))

    implementation("io.ktor:ktor-server-core:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty:${ktorVersion}")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("com.mipt.kotlin.ktor.practice.MainKt")
}