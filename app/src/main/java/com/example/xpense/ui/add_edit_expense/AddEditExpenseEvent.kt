package com.example.xpense.ui.add_edit_expense

import android.content.Context
import androidx.navigation.NavHostController

sealed class AddEditExpenseEvent {
    data class EnteredTitle(val value: String) : AddEditExpenseEvent()
    data class EnteredAmount(val value: String) : AddEditExpenseEvent()
    data class EnteredNote(val value: String) : AddEditExpenseEvent()
    data class EnteredTags(val value: String) : AddEditExpenseEvent()
    data class SaveEditExpense(val context: Context, val navHostController: NavHostController) :
        AddEditExpenseEvent()
    object OnExpandedChange : AddEditExpenseEvent()
    object OnDismissRequest : AddEditExpenseEvent()
    data class ChangeSelectedOption(val value: String) : AddEditExpenseEvent()

    object OpenDialog : AddEditExpenseEvent()
    object CloseDialog:AddEditExpenseEvent()


}