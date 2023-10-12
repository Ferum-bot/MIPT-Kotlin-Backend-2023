package com.mipt.kotlin.coroutines.practice.remote.model

import java.time.Instant

data class RemoteData(
    val identifier: Int,
    val payload: Payload,
) {

    data class Payload(
        val taskResult: Int,
        val createdAt: Instant,
    )
}
