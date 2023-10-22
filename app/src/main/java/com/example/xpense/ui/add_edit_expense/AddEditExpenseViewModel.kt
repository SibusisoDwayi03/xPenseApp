package com.example.xpense.ui.add_edit_expense

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xpense.data.Expense
import com.example.xpense.data.ExpenseRepository
import com.example.xpense.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditExpenseViewModel @Inject constructor(
    private val userDataRepository: ExpenseRepository,
    savedStateHandle: SavedStateHandle,

    ) : ViewModel() {

    private val transactionId: Int = checkNotNull(savedStateHandle["id"])
    private val previousScreen: String = checkNotNull(savedStateHandle["previousScreen"])


    private val _title = mutableStateOf(
        AddEditExpenseTextFieldState(
            hint = "Enter an Item.."
        )
    )
    val title: State<AddEditExpenseTextFieldState> = _title

    private val _tags = mutableStateOf(
        AddEditExpenseTextFieldState(
            hint = "Item Description"
        )
    )
    val tags: State<AddEditExpenseTextFieldState> = _tags

    private val _amount = mutableStateOf(
        AddEditExpenseTextFieldState(
            hint = "Enter the Amount.."
        )
    )
    val amount: State<AddEditExpenseTextFieldState> = _amount

    private val _note = mutableStateOf(
        AddEditExpenseTextFieldState(
            hint = "Date.."
        )
    )
    val note: State<AddEditExpenseTextFieldState> = _note

    private val _transactionType = mutableStateOf(
        AddEditExpenseDropDownMenuState(
            hint = " Type"
        )
    )
    val transactionType: State<AddEditExpenseDropDownMenuState> = _transactionType

    init {

        if (previousScreen == Screen.TransactionDetails.route) {
            viewModelScope.launch {
                userDataRepository.getTransactionById(transactionId).collect{
                    _title.value = title.value.copy(
                        text = it.title
                    )
                    _amount.value = amount.value.copy(
                        text = it.amount.toString()
                    )
                    _note.value = note.value.copy(
                        text = it.datee
                    )
                    _tags.value = tags.value.copy(
                        text = it.description
                    )
                    _transactionType.value = transactionType.value.copy(
                        selectedOption = it.transactionType
                    )
                    _warnigState.value = warnigState.value.copy(
                        text = "Do you want to quit changes?"
                    )

                }
            }
        }
    }

    private val _warnigState = mutableStateOf(WarnigState())
    val warnigState: State<WarnigState> = _warnigState


    fun onEvent(event: AddEditExpenseEvent) {
        when (event) {
            is AddEditExpenseEvent.EnteredTitle -> {
                _title.value = title.value.copy(
                    text = event.value
                )

            }
            is AddEditExpenseEvent.EnteredAmount -> {
                _amount.value = amount.value.copy(
                    text = event.value
                )
            }
            is AddEditExpenseEvent.EnteredNote -> {
                _note.value = note.value.copy(
                    text = event.value
                )
            }


            is AddEditExpenseEvent.OnExpandedChange -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = !_transactionType.value.isExpanded
                )
            }
            is AddEditExpenseEvent.OnDismissRequest -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = false
                )
            }
            is AddEditExpenseEvent.ChangeSelectedOption -> {
                _transactionType.value = transactionType.value.copy(
                    selectedOption = event.value
                )
            }
            is AddEditExpenseEvent.EnteredTags -> {
                _tags.value = tags.value.copy(
                    text = event.value
                )
            }
            is AddEditExpenseEvent.SaveEditExpense -> {
                if (
                    _title.value.text != "" && _tags.value.text != "" && _transactionType.value.selectedOption != "" && _note.value.text != "" && _amount.value.text != ""
                ) {
                    viewModelScope.launch {
                        if (previousScreen == Screen.TransactionDetails.route){
                            userDataRepository.update(
                                Expense(
                                    id = transactionId,
                                    title = _title.value.text,
                                    amount = _amount.value.text.toLong(),
                                    transactionType = _transactionType.value.selectedOption,
                                    description = _tags.value.text,
                                    date = "getFormattedTime()",
                                    datee = _note.value.text


                                )
                            )
                        }else{
                        userDataRepository.insert(
                            Expense(
                                title = _title.value.text,
                                amount = _amount.value.text.toLong(),
                                transactionType = _transactionType.value.selectedOption,
                                description = _tags.value.text,
                                datee = _note.value.text,
                                date = "getFormattedTime()"


                            )
                        )
                    }}

                    event.navHostController.navigateUp()

                } else {
                    Toast.makeText(
                        event.context,
                        "Please fill-up all fields.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            is AddEditExpenseEvent.OpenDialog -> {
                _warnigState.value = warnigState.value.copy(
                    state = true
                )
            }
            is AddEditExpenseEvent.CloseDialog -> {
                _warnigState.value = warnigState.value.copy(
                    state = false
                )
            }


        }

    }


}