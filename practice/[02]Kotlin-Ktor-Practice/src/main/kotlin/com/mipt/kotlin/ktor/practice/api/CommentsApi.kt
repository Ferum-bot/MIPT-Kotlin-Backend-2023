package com.mipt.kotlin.ktor.practice.api

import com.mipt.kotlin.ktor.practice.api.model.CreateCommentRequest
import com.mipt.kotlin.ktor.practice.repository.CommentsRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.commentsApi() {
    routing {

        val commentsRepository by inject<CommentsRepository>()

        get("/comments/all") {
            val comments = commentsRepository.getAll()
            call.respond(comments)
        }

        get("/comment/{id}/get") {
            val commentId = call.parameters["id"]?.toLong() ?: 0

            val comment = commentsRepository.getById(commentId)

            if (comment == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(comment)
            }
        }

        put("/comment/create") {
            val request = call.receive<CreateCommentRequest>()

            val createdComment = commentsRepository.createComment(request.commentText)

            call.respond(createdComment)
        }


    }
}