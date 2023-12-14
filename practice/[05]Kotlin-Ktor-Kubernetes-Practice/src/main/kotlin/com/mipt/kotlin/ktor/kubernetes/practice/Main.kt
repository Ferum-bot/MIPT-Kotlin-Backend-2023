package com.mipt.kotlin.ktor.kubernetes.practice

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.Instant

@Serializable
data class Response(
    val message: String,
    val currentTime: String,
)

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        configureServer()
    }.start(wait = true)
}

private fun Application.configureServer() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    routing {

        get("/metrics") {
            call.respond(HttpStatusCode.OK)
        }

        get("/api/message") {
            val response = Response(
                message = "Hello world",
                currentTime = Instant.now().toString()
            )
            call.respond(response)
        }
    }
}