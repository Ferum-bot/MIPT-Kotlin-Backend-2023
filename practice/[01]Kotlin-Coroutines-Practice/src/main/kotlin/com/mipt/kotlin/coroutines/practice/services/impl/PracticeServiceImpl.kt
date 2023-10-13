package com.mipt.kotlin.coroutines.practice.services.impl

import com.mipt.kotlin.coroutines.practice.compute.model.Task
import com.mipt.kotlin.coroutines.practice.compute.model.TaskComputeResult
import com.mipt.kotlin.coroutines.practice.di.DependencyProvider
import com.mipt.kotlin.coroutines.practice.services.PracticeService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class PracticeServiceImpl : PracticeService {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val computeScope = CoroutineScope(Dispatchers.Default)

    private val remoteClient = DependencyProvider.provideRemoteClient()
    private val computeExecutor = DependencyProvider.provideComputeExecutor()

    override suspend fun startWork() {
//        loadRemote()

        compute()
    }

    private suspend fun compute() {
        val computeTasks = DependencyProvider.generateTasksToCompute()
        val computeJobs = mutableListOf<Deferred<TaskComputeResult>>()

        computeTasks.forEach { task ->
            val deferred = computeScope.async {
                task.asyncComputeChildren(this)
            }
            computeJobs.add(deferred)
        }

        val computeResult = computeJobs.awaitAll()
        computeResult.forEach { result ->
            println("Compute task completed! Result: ${result.computeResult}")
        }
    }

    private suspend fun Task.GenericComputeTask.asyncComputeChildren(scope: CoroutineScope): TaskComputeResult {
        val computeJobs = mutableListOf<Deferred<TaskComputeResult>>()
        childTasks.forEach { task ->
            val deferred = scope.async {
                computeExecutor.computeTask(task)
            }
            computeJobs.add(deferred)
        }

        val computeResult = computeJobs.awaitAll()
        val realResult = computeResult.fold(0) { currentValue, currentCompute ->
            return@fold when (tasksOperator) {
                Task.Operator.MINUS -> currentValue - currentCompute.computeResult
                Task.Operator.PLUS -> currentValue + currentCompute.computeResult
            }
        }

        return TaskComputeResult(realResult)
    }

    private suspend fun loadRemote() {
        val remoteIdentifiers = DependencyProvider.provideRemoteDataIdentifiers()
        val remoteJobs = mutableListOf<Job>()
        val supervisorJob = SupervisorJob()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            val name = coroutineContext[CoroutineName.Key]?.name
                ?: "DefaultName"
            println("[$name]Received error: ${throwable.message}")
        }

        remoteIdentifiers.forEach { identifier ->
            val coroutineName = CoroutineName("Coroutine(${identifier})")
            val job = ioScope.launch(context = exceptionHandler + supervisorJob + coroutineName) {
                remoteClient.loadRemotePayload(identifier)
            }
            remoteJobs.add(job)
        }

        remoteJobs.joinAll()
    }
}