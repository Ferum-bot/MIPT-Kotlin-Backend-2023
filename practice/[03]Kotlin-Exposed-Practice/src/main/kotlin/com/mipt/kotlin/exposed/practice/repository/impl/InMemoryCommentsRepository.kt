package com.mipt.kotlin.exposed.practice.repository.impl

import com.mipt.kotlin.exposed.practice.model.Comment
import com.mipt.kotlin.exposed.practice.repository.CommentsRepository
import java.time.Instant
import kotlin.random.Random

class InMemoryCommentsRepository: CommentsRepository {

    private val comments: MutableSet<Comment> = mutableSetOf()

    override fun getAll(): Collection<Comment> {
        return comments.toList()
    }

    override fun getById(id: Long): Comment? {
        return comments.find { it.id == id }
    }

    override fun createComment(commentText: String): Comment {
        val createdComment = Comment(
            id = Random.nextLong(),
            text = commentText,
            createdAt = Instant.now().toString(),
        )
        comments.add(createdComment)
        return createdComment
    }
}