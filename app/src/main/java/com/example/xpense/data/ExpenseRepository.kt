package com.example.xpense.data

import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun insert(expense: Expense)
    suspend fun update(expense: Expense)
    suspend fun getAllTransactions(): Flow<List<Expense>>
    suspend fun getTransactionById(id:Int): Flow<Expense>
    suspend fun deleteTransactionById(id:Int)

}