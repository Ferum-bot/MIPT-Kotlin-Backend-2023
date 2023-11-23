package com.mipt.kotlin.coroutines.advanced

import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main(args: Array<String>): Unit = runBlocking {
    val job = launch {
        try {
            val result = doWork("Hello world")
            println(result)
        } catch (ex: Exception) {
            println(ex.message)
            val result = doWork("Do work after exception")
            println(result)
        }
    }
    delay(200L)
    job.cancelAndJoin()
}

suspend fun doWork(name: String): String {
    println("First delay done")
    delay(2500L)
    return "Done: $name"
}