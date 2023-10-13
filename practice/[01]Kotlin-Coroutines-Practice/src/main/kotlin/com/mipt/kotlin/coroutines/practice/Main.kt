package com.mipt.kotlin.coroutines.practice

import com.mipt.kotlin.coroutines.practice.services.PracticeService
import com.mipt.kotlin.coroutines.practice.services.impl.PracticeServiceImpl
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val practiceService: PracticeService = PracticeServiceImpl()

    runBlocking {
        practiceService.startWork()
    }
}