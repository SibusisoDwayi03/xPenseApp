package com.example.xpense.ui.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
//import com.example.xpense.presentation.common.components.TransactionCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Transactions(
    navHostController: NavHostController,
    viewModel: ExpenseViewModel = hiltViewModel()

) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val transactionList by viewModel.transactions

        val options = listOf("All", "Expense")

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = " List of Expenses", fontSize = 20.sp)

            ExposedDropdownMenuBox(
                expanded = viewModel.transactionType.value.isExpanded,
                onExpandedChange = {
                    viewModel.onEvent(ExpenseEvent.OnExpandedChange)
                },
                modifier = Modifier.width(140.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = viewModel.transactionType.value.selectedOption,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewModel.transactionType.value.isExpanded
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color(0xFF413F40),
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                ExposedDropdownMenu(
                    expanded = viewModel.transactionType.value.isExpanded,
                    onDismissRequest = {
                        viewModel.onEvent(ExpenseEvent.OnDismissRequest)
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(
                                    ExpenseEvent.ChangeSelectedOption(
                                        selectionOption
                                    )
                                )
                                viewModel.onEvent(ExpenseEvent.OnDismissRequest)
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (transactionList.list.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No recent transactions..",
                    color = Color(0xFF424141),
                )
            }
//
        }
//        else {
//            LazyColumn(
//                contentPadding = PaddingValues(
//                    8.dp, 0.dp, 8.dp, 64.dp
//                ),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(transactionList.list) {
//                    TransactionCard(transaction = it) {
//                        navHostController.navigate(Screen.TransactionDetails.withArgs(it.id.toString()))
//
//                    }
                }

            }
//        }
//    }
//}


