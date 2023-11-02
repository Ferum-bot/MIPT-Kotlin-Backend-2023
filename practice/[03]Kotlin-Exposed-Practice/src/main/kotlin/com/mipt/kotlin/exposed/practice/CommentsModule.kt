package com.mipt.kotlin.exposed.practice

import com.mipt.kotlin.exposed.practice.repository.CommentsRepository
import com.mipt.kotlin.exposed.practice.repository.impl.DatabaseCommentsRepository
import com.mipt.kotlin.exposed.practice.repository.impl.InMemoryCommentsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commentsModule = module {
    singleOf(::DatabaseCommentsRepository) bind CommentsRepository::class
}