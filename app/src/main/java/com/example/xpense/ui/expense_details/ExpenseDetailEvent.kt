package com.example.xpense.ui.expense_details

import android.content.Context
import androidx.navigation.NavHostController
import com.example.xpense.data.Expense

sealed class ExpenseDetailEvent {
    data class Share(val context:Context) : ExpenseDetailEvent()
    data class Edit(val expense: Expense) : ExpenseDetailEvent()
    data class Delete(val navHostController: NavHostController, val id: Int) : ExpenseDetailEvent()
}