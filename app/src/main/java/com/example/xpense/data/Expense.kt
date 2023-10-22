package com.example.xpense.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val amount: Long,
    val transactionType: String,
    val description: String,
    val datee: String,
    val date: String
)
