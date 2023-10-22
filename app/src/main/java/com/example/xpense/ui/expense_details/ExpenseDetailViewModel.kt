package com.example.xpense.ui.expense_details

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xpense.data.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseDetailViewModel @Inject constructor(
    private val userDataRepository: ExpenseRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val transactionId: Int = checkNotNull(savedStateHandle["transactionId"])
    private val _currTransaction = mutableStateOf(ExpenseState())
    val currTransaction: State<ExpenseState> = _currTransaction

    init {
        viewModelScope.launch {
            userDataRepository.getTransactionById(transactionId).collect {
                _currTransaction.value = currTransaction.value.copy(
                    expense = it
                )
            }
        }

    }

    fun onEvent(event: ExpenseDetailEvent) {
        when (event) {
            is ExpenseDetailEvent.Delete -> {
                event.navHostController.navigateUp()

                viewModelScope.launch {
                    userDataRepository.deleteTransactionById(event.id)
                }

            }
            is ExpenseDetailEvent.Edit -> {}
            is ExpenseDetailEvent.Share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        if (_currTransaction.value.expense?.transactionType == "Expense") {
                            "I paid $${_currTransaction.value.expense?.amount} for ${_currTransaction.value.expense!!.title}."
                        } else {
                            "I earned $${_currTransaction.value.expense?.amount} from ${_currTransaction.value.expense!!.title}."

                        }
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                event.context.startActivity(shareIntent)

            }
        }
    }


}