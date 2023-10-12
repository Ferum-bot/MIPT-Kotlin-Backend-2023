package com.mipt.kotlin.coroutines.practice.di

import com.mipt.kotlin.coroutines.practice.compute.ComputeExecutor
import com.mipt.kotlin.coroutines.practice.compute.impl.DelayComputeExecutor
import com.mipt.kotlin.coroutines.practice.compute.model.Task
import com.mipt.kotlin.coroutines.practice.remote.RemoteClient
import com.mipt.kotlin.coroutines.practice.remote.impl.DelayRemoteClient

object DependencyProvider {

    fun provideComputeExecutor(): ComputeExecutor {
        return DelayComputeExecutor()
    }

    fun provideRemoteClient(): RemoteClient {
        return DelayRemoteClient()
    }

    fun provideRemoteDataIdentifiers(): Collection<Int> {
        return listOf()
    }

    fun provideTasksToCompute(): Collection<Task.GenericComputeTask> {
        return listOf()
    }
}