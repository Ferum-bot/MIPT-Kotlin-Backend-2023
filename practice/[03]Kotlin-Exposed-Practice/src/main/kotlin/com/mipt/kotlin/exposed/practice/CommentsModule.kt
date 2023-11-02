package com.mipt.kotlin.exposed.practice

import com.mipt.kotlin.exposed.practice.repository.CommentsRepository
import com.mipt.kotlin.exposed.practice.repository.impl.DefaultCommentsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commentsModule = module {
    singleOf(::DefaultCommentsRepository) bind CommentsRepository::class
}