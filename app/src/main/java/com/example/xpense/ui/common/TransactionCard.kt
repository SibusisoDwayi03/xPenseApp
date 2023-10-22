package com.example.xpense.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpense.data.Expense

@Composable
fun TransactionCard(expense: Expense, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(Color(0xFF262629))
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Center vertically
    ) {
        Column {
            Text(
                text = expense.title,
                color = Color.White,
                fontSize = 16.sp,
            )

            Text(
                modifier = Modifier.width(64.dp),
                text = expense.description,
                color = Color.White.copy(0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = if (expense.transactionType == "Expense") {
                    "-R${expense.amount}"
                } else {
                    "+R${expense.amount}"
                },
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}
