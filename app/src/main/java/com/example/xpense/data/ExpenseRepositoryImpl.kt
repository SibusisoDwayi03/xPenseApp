package com.example.xpense.data

import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl(
    private val dao: ExpenseDao,
) : ExpenseRepository {

    override suspend fun insert(expense: Expense) {
        dao.insertTransaction(expense)
    }

    override suspend fun update(expense: Expense) {
        dao.updateTransaction(expense)
    }

    override suspend fun getAllTransactions(): Flow<List<Expense>> {
        return dao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): Flow<Expense> {
       return dao.getTransactionById(id)
    }

    override suspend fun deleteTransactionById(id: Int) {
        dao.deleteTransactionById(id)
    }


}