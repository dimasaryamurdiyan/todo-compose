package com.singaludra.todoapp.ui.addentry

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.singaludra.todoapp.ui.SharedViewModel
import com.singaludra.todoapp.ui.addentry.components.InputFieldSection
import com.singaludra.todoapp.ui.theme.ToDoAppTheme
import com.singaludra.todoapp.utils.DateUtils.makeDatePicker
import java.util.Date

@Composable
fun AddScreen(
    navController: NavController, viewModel: SharedViewModel? = null
) {
    val scaffoldState = rememberScaffoldState()
    val localFocusManager = LocalFocusManager.current
    val selectedDate = remember {
        mutableStateOf(Date())
    }
    val mDatePickerDialog =
        makeDatePicker(selectedDate, LocalContext.current, isMinDateToday = true)
    val focusRequester = remember { FocusRequester() }
    var textState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }
    val scroll = rememberScrollState(0)
    val localDensity = LocalDensity.current
    var heightIs by remember {
        mutableStateOf(0.dp)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .border(1.dp, Color.Gray, CircleShape)
                                .size(36.dp)
                                .padding(8.dp)
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    navController.navigateUp()
                                }
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 0.dp,
                modifier = Modifier.padding(8.dp)
            )
    }, content = {
        InputFieldSection(
            paddingValues = it,
            textState = textState,
            descriptionState = descriptionState,
            selectedDate = selectedDate,
            scroll = scroll,
            focusRequester = focusRequester,
            localFocusManager = localFocusManager,
            mDatePickerDialog = mDatePickerDialog,
            onGloballyPositionedChange = {
                heightIs = with(localDensity) { it.size.height.toDp() }} ,
            onValueChange = { title -> textState = title } ,
            onValueDescriptionChange = { description -> descriptionState = description},
            updateTodo = {
                viewModel?.addToDoEntry(
                    title = textState,
                    desc = descriptionState,
                    date = selectedDate.value
                )
            },
            navigateBack = { navController.navigateUp() }
        )
    })

}



@Preview(showBackground = true)
@Composable
fun previewAddScreen(){
    ToDoAppTheme {
        AddScreen(navController = rememberNavController())
    }
}