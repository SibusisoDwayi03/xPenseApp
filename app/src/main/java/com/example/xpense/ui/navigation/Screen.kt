package com.example.xpense.ui.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen(route = "Home")
    object TransactionDetails : Screen(route = "Expense_details")
    object AddEditTransaction : Screen(route = "add_transaction")
    object Transactions : Screen("expenses")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
