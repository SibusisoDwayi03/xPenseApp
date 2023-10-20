package com.example.xpense.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xpense.data.Transaction
import com.example.xpense.data.TransactionDao

@Database(
    entities = [Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class TransactionsDB : RoomDatabase() {
    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transactions_db"
    }
}