package com.example.xpense.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Expense::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDB : RoomDatabase() {
    abstract val expenseDao: ExpenseDao

    companion object {
        const val DATABASE_NAME = "transactions_db"
    }
}