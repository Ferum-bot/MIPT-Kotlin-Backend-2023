package com.mipt.kotlin.exposed.practice.repository

import com.mipt.kotlin.ktor.practice.model.Comment

interface CommentsRepository {

    fun getAll(): Collection<Comment>

    fun getById(id: Long): Comment?

    fun createComment(commentText: String): Comment
}