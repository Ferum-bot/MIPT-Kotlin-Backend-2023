package com.mipt.kotlin.ktor.practice.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequest(
    val commentText: String
)
