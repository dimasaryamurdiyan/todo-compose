package com.singaludra.todoapp.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.ui.common.ErrorBox

@Composable
fun ToDoListSection(
    dateText: String,
    dailyData: List<ToDo>,
    onClick: (todo: ToDo) -> Unit,
    onDelete: (todo: ToDo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = ("$dateText tasks").uppercase(),
            color = Color.DarkGray,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (dailyData.isEmpty()) ErrorBox(
            text = "You have no tasks!",
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.primary
        )
        else LazyColumn(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(dailyData.size) {
                ToDoSection(
                    todo = dailyData[it],
                    onClick = onClick,
                    onDelete = onDelete,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}