package com.mipt.kotlin.exposed.practice.repository.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CommentsTable : LongIdTable("comments") {

    val text: Column<String> = varchar("text", length = 1024)

    val createdAt: Column<Instant> = timestamp("created_at")
        .clientDefault { Clock.System.now() }
}