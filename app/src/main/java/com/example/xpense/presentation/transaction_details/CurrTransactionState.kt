package com.example.xpense.presentation.transaction_details

import com.example.xpense.data.Transaction
//import com.example.xpense.presentation.add_edit_transaction.getFormattedTime


data class CurrTransactionState(
    val transaction: Transaction? = Transaction(
        id = -1,
        title = "",
        amount = 0,
        transactionType = "",
        description = "",
        datee = "",
        date = "getFormattedTime()"
    )

)