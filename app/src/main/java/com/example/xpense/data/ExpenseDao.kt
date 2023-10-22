package com.example.xpense.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(expense: Expense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(expense: Expense)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Expense>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: Int): Flow<Expense>

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransactionById(id: Int)

}
