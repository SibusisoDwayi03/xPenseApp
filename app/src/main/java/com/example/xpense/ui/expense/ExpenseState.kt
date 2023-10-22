package com.example.xpense.ui.expense

import com.example.xpense.data.Expense

data class ExpenseState(
    val list: List<Expense> = mutableListOf()
)