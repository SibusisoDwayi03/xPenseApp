package com.example.xpense.presentation.transactions

import com.example.xpense.data.Transaction

data class TransactionsState(
    val list: List<Transaction> = mutableListOf()
)