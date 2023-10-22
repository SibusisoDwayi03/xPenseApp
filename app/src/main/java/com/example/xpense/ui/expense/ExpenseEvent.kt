package com.example.xpense.ui.expense

sealed class ExpenseEvent {
    object OnExpandedChange : ExpenseEvent()
    object OnDismissRequest : ExpenseEvent()
    data class ChangeSelectedOption(val value: String) : ExpenseEvent()
}
