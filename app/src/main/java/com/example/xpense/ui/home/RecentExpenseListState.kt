package com.example.xpense.ui.home

import com.example.xpense.data.Expense

data class RecentExpenseListState(
    val list: List<Expense> = mutableListOf()
)
