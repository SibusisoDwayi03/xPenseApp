package com.example.xpense.ui.expense_details

import com.example.xpense.data.Expense
//import com.example.xpense.presentation.add_edit_transaction.getFormattedTime


data class ExpenseState(
    val expense: Expense? = Expense(
        id = -1,
        title = "",
        amount = 0,
        transactionType = "",
        description = "",
        datee = "",
        date = "getFormattedTime()"
    )

)