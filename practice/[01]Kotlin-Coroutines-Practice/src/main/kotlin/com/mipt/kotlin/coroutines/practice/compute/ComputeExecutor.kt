package com.mipt.kotlin.coroutines.practice.compute

import com.mipt.kotlin.coroutines.practice.compute.model.Task
import com.mipt.kotlin.coroutines.practice.compute.model.TaskComputeResult

interface ComputeExecutor {

    suspend fun computeTask(task: Task.SingleComputeTask): TaskComputeResult
}