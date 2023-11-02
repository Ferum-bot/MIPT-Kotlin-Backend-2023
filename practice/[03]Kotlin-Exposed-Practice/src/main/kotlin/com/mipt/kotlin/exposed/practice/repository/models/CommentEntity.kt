package com.mipt.kotlin.exposed.practice.repository.models

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CommentEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object: LongEntityClass<CommentEntity>(CommentsTable)

    var text by CommentsTable.text

    var createdAt by CommentsTable.createdAt
}