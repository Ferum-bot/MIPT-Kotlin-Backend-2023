package com.mipt.kotlin.exposed.practice

import com.mipt.kotlin.exposed.practice.api.commentsApi
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

fun main(args: Array<String>) {

    embeddedServer(Netty, port = 8080) {

        configureServer()

        commentsApi()
    }.start(wait = true)

}

fun Application.configureServer() {
    install(Koin) {
        modules(commentsModule)
    }
    install(CallLogging) {
        level = Level.DEBUG
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
}