package com.mipt.kotlin.exposed.practice.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequest(
    val commentText: String
)
