package com.mipt.kotlin.exposed.practice

import com.mipt.kotlin.exposed.practice.api.commentsApi
import com.mipt.kotlin.exposed.practice.repository.models.CommentsTable
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

fun main(args: Array<String>) {

    embeddedServer(Netty, port = 8080) {
        configureDatabase()

        configureServer()

        commentsApi()
    }.start(wait = true)

}

private fun Application.configureServer() {
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

private fun configureDatabase() {
    val url = "jdbc:postgresql://localhost:9000/practice_db"
    val username = "mipt_kotlin_backend"
    val password = "simple_password"
    val database = Database.connect(url = url, user = username, password = password)
    database.initSchema()
}

private fun Database.initSchema() {
    transaction(this) {
        SchemaUtils.create(CommentsTable)
    }
}