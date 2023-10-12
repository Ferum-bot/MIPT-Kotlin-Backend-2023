package com.mipt.kotlin.coroutines.practice.compute.model

sealed class Task(val isSingle: Boolean) {

    data class SingleComputeTask(
        val leftOperand: Int,
        val rightOperand: Int,
        val operator: Operator,
    ) : Task(isSingle = true)

    data class GenericComputeTask(
        val childTasks: Collection<SingleComputeTask>,
        val tasksOperator: Operator,
    ) : Task(isSingle = false)

    enum class Operator {
        PLUS,
        MINUS;
    }
}
