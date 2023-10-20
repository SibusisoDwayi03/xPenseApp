package com.example.xpense.data

import com.example.xpense.data.TransactionDao
import com.example.xpense.data.Transaction
import com.example.xpense.data.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val dao: TransactionDao,
) : TransactionRepository {

    override suspend fun insert(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        dao.updateTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): Flow<Transaction> {
       return dao.getTransactionById(id)
    }

    override suspend fun deleteTransactionById(id: Int) {
        dao.deleteTransactionById(id)
    }


}