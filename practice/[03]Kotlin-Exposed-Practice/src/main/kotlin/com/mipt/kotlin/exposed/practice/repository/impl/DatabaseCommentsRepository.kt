package com.mipt.kotlin.exposed.practice.repository.impl

import com.mipt.kotlin.exposed.practice.model.Comment
import com.mipt.kotlin.exposed.practice.repository.CommentsRepository
import com.mipt.kotlin.exposed.practice.repository.models.CommentEntity
import com.mipt.kotlin.exposed.practice.repository.models.CommentsTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

class DatabaseCommentsRepository : CommentsRepository {

    companion object {

        private const val MAX_COMMENTS_COUNT = 100
    }

    override fun getAll(): Collection<Comment> {
        return transaction {
            val query = CommentsTable.selectAll().limit(MAX_COMMENTS_COUNT)
            val entities = CommentEntity.wrapRows(query)
            entities.map { it.toComment() }
        }
    }

    override fun getById(id: Long): Comment? {
        return transaction { CommentEntity.findById(id)?.toComment() }
    }

    override fun createComment(commentText: String): Comment {
        return transaction {
            val commentId = Random.nextLong()
            val createdEntity = CommentEntity.new(commentId) {
                text = commentText
            }
            createdEntity.toComment()
        }

    }

    private fun CommentEntity.toComment(): Comment {
        return Comment(
            id = id.value,
            text = text,
            createdAt = createdAt.toString()
        )
    }
}