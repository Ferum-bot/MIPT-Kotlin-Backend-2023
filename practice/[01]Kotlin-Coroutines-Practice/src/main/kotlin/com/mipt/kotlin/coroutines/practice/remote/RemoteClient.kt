package com.mipt.kotlin.coroutines.practice.remote

import com.mipt.kotlin.coroutines.practice.remote.model.RemoteData

interface RemoteClient {

    suspend fun loadRemotePayload(identifier: Int): RemoteData
}