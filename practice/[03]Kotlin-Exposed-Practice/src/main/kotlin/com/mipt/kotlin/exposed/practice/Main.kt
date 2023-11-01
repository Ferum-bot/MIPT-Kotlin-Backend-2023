import com.mipt.kotlin.ktor.practice.api.commentsApi
import com.mipt.kotlin.ktor.practice.commentsModule
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin

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
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
}