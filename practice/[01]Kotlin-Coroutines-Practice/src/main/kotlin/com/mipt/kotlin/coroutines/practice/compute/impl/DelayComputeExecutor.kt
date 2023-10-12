package com.mipt.kotlin.coroutines.practice.compute.impl

import com.mipt.kotlin.coroutines.practice.compute.ComputeExecutor
import com.mipt.kotlin.coroutines.practice.compute.model.Task
import com.mipt.kotlin.coroutines.practice.compute.model.TaskComputeResult
import kotlinx.coroutines.delay

class DelayComputeExecutor : ComputeExecutor {

    override suspend fun computeTask(task: Task.SingleComputeTask): TaskComputeResult {
        println("Received single task to compute: ${task.leftOperand} ${task.operator} ${task.rightOperand}")
        delay(350L)

        var result = task.leftOperand
        when (task.operator) {
            Task.Operator.MINUS -> result -= task.rightOperand
            Task.Operator.PLUS -> result += task.rightOperand
        }

        delay(150L)
        return TaskComputeResult(computeResult = result)
    }
}