package com.singaludra.todoapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singaludra.todoapp.data.Resource
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.ui.SharedViewModel
import com.singaludra.todoapp.ui.common.ErrorBox
import com.singaludra.todoapp.ui.common.Loading
import com.singaludra.todoapp.utils.DateUtils
import com.singaludra.todoapp.utils.filterDate
import java.util.Date

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    entries: Resource<List<ToDo>>,
    selectedDate: MutableState<Date>,
    viewModel: SharedViewModel
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(12.dp)
            .background(MaterialTheme.colors.background)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "What's up Bro!?",
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(36.dp))
        when (entries) {
            is Resource.Error -> ErrorBox(
                text = (entries as Resource.Error).errorModel.getErrorMessage()
            )
            is Resource.Loading -> Loading()
            is Resource.Success -> {
                Spacer(modifier = Modifier.height(24.dp))
                ToDoListSection(
                    dateText = DateUtils.calculateDateText(selectedDate.value),
                    dailyData = (entries as Resource.Success).data.filterDate(
                        selectedDate.value
                    ),
                    onClick = { toDoEntry ->
                        viewModel.editToDoEntry(toDoEntry)
                    },
                    onDelete = { toDoEntry ->
                        viewModel.deleteTodo(toDoEntry)
                    }
                )
            }
        }
    }
}
