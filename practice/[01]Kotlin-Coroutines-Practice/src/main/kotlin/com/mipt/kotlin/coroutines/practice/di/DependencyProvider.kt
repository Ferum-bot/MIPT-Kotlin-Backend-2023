package com.mipt.kotlin.coroutines.practice.di

import com.mipt.kotlin.coroutines.practice.compute.ComputeExecutor
import com.mipt.kotlin.coroutines.practice.compute.impl.DelayComputeExecutor
import com.mipt.kotlin.coroutines.practice.compute.model.Task
import com.mipt.kotlin.coroutines.practice.remote.RemoteClient
import com.mipt.kotlin.coroutines.practice.remote.impl.DelayRemoteClient
import kotlin.random.Random

object DependencyProvider {

    private const val REMOTE_IDENTIFIERS_COUNT = 500
    private const val SINGLE_TASKS_PER_GENERIC_COUNT = 200
    private const val GENERIC_TASKS_COUNT = 10

    fun provideComputeExecutor(): ComputeExecutor {
        return DelayComputeExecutor()
    }

    fun provideRemoteClient(): RemoteClient {
        return DelayRemoteClient()
    }

    fun provideRemoteDataIdentifiers(): Collection<Int> {
        val identifiersRange = 0..REMOTE_IDENTIFIERS_COUNT
        return identifiersRange.toList()
    }

    fun generateTasksToCompute(): Collection<Task.GenericComputeTask> {
        val genericTasks = mutableListOf<Task.GenericComputeTask>()

        repeat(GENERIC_TASKS_COUNT) {
            val genericTaskChildren = mutableListOf<Task.SingleComputeTask>()

            repeat(SINGLE_TASKS_PER_GENERIC_COUNT) {
                val generatedTak = generateTask()
                genericTaskChildren.add(generatedTak)
            }

            val genericTask = Task.GenericComputeTask(
                childTasks = genericTaskChildren,
                tasksOperator = generateOperator()
            )
            genericTasks.add(genericTask)
        }

        return genericTasks
    }

    private fun generateTask(): Task.SingleComputeTask {
        return Task.SingleComputeTask(
            leftOperand = Random.nextInt(),
            rightOperand = Random.nextInt(),
            operator = generateOperator()
        )
    }

    private fun generateOperator(): Task.Operator {
        val randomValue = Random.nextInt()
        return if (randomValue % 2 == 0) {
            Task.Operator.PLUS
        } else {
            Task.Operator.MINUS
        }
    }
}