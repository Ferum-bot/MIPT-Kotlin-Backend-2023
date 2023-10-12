package com.mipt.kotlin.coroutines.practice.remote.impl

import com.mipt.kotlin.coroutines.practice.remote.RemoteClient
import com.mipt.kotlin.coroutines.practice.remote.model.RemoteData
import kotlinx.coroutines.delay
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextInt

class DelayRemoteClient : RemoteClient {

    companion object {
        private val possibleDelays = listOf(40L, 90L, 200L, 350L, 500L, 700L, 1200L)
    }

    override suspend fun loadRemotePayload(identifier: Int): RemoteData {
        if (identifier % 100 == 41) {
            println("Remote data with id: $identifier not found")
            throwNotFoundError(identifier)
        }
        val delayIndexRange = 0..possibleDelays.lastIndex
        val delayValue = possibleDelays[Random.nextInt(delayIndexRange)]

        println("Loading remote data for id: $identifier")
        delay(delayValue)

        return RemoteData(
            identifier,
            payload = RemoteData.Payload(
                taskResult = Random.nextInt(),
                createdAt = Instant.now(),
            )
        )
    }

    private fun throwNotFoundError(identifier: Int): Nothing {
        throw IllegalArgumentException("Remote Data with id: $identifier not found")
    }
}