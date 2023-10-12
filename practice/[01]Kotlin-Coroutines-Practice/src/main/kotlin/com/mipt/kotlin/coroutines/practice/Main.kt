package com.mipt.kotlin.coroutines.practice

import com.mipt.kotlin.coroutines.practice.services.PracticeService
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val practiceService: PracticeService = TODO("Add your implementation here")

    runBlocking {
        practiceService.startWork()
    }
}