package com.mipt.kotlin.exposed.practice.model

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Comment(
    val id: Long,
    val text: String,
    val createdAt: String
)
