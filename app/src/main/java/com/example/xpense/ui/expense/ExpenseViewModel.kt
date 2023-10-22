package com.example.xpense.ui.expense

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xpense.data.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val userDataRepository: ExpenseRepository
) : ViewModel() {

    private val _transactions = mutableStateOf(ExpenseState())
    val transactions: State<ExpenseState> = _transactions

    private val _transactionType = mutableStateOf(
        ExpenseDropDownMenuState(
            selectedOption = "Expenses"
        )
    )
    val transactionType: State<ExpenseDropDownMenuState> = _transactionType

    init {
        viewModelScope.launch {
            userDataRepository.getAllTransactions().collect {
                _transactions.value = transactions.value.copy(

                    list = it.reversed()
                )
            }
        }
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.OnExpandedChange -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = !_transactionType.value.isExpanded
                )
            }
            is ExpenseEvent.OnDismissRequest -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = false
                )
            }
            is ExpenseEvent.ChangeSelectedOption -> {
                _transactionType.value = transactionType.value.copy(
                    selectedOption = event.value
                )
                viewModelScope.launch {
                    userDataRepository.getAllTransactions().collect {
                        _transactions.value = transactions.value.copy(

                            list = when (_transactionType.value.selectedOption) {
                                "Expense" -> {
                                    it.filter { transaction ->
                                        transaction.transactionType == "Expense"

                                    }.reversed()
                                }
                                else -> {
                                    it.reversed()
                                }
                           //     "Income" -> {
                         //           it.filter { transaction ->
                          //              transaction.transactionType == "Income"

                          //          }.reversed()
                       //         }
                       //         else -> {
                      //              it.reversed()
                     //           }
                            }
                        )
                    }
                }
            }
        }
    }

}