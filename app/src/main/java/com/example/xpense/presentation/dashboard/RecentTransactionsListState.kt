package com.example.xpense.presentation.dashboard

import com.example.xpense.data.Transaction

data class RecentTransactionsListState(
    val list: List<Transaction> = mutableListOf()
)
