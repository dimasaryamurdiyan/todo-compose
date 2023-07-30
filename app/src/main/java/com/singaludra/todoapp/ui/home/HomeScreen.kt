package com.singaludra.todoapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.singaludra.todoapp.navigation.Screen
import com.singaludra.todoapp.ui.SharedViewModel
import com.singaludra.todoapp.ui.home.components.HomeScreenContent
import com.singaludra.todoapp.ui.home.components.HomeScreenTopBar
import java.util.Date

@Composable
fun HomeScreen(
    navController: NavController, viewModel: SharedViewModel
) {
    val entries by viewModel.toDoEntries.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val selectedDate = remember {
        mutableStateOf(Date())
    }
    val mDatePickerDialog = com.singaludra.todoapp.utils.DateUtils.makeDatePicker(selectedDate, LocalContext.current)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar(
                expanded = viewModel.menuExpanded,
                onExpand = viewModel::onMenuExpanded,
                onDismiss = viewModel::onMenuCollapsed,
                onDeleteCompletedClick = { viewModel.deleteAllCompletedTodo() },
                onDeleteAllClick = { viewModel.deleteAllTodo() }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Column (
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
            ){
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    contentColor = MaterialTheme.colors.onPrimary,
                    onClick = {
                        navController.navigate(Screen.AddScreen.route)
                    })
                {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
                }
                Spacer(modifier = Modifier.height(8.dp))
                ExtendedFloatingActionButton(
                    text = {
                        Text(text = "Filter Tasks", color = Color.White)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.DateRange,
                            contentDescription = "Filter by Date",
                            tint = Color.White,
                        )
                    },
                    onClick = { mDatePickerDialog.show() },
                    containerColor = colors.secondaryVariant,
                )
            }
        },
        content = {
            HomeScreenContent(
                paddingValues = it,
                entries = entries,
                selectedDate = selectedDate,
                viewModel = viewModel,
            )
        })
}