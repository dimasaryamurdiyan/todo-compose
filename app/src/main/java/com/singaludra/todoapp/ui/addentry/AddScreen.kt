package com.singaludra.todoapp.ui.addentry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.singaludra.todoapp.ui.SharedViewModel
import com.singaludra.todoapp.ui.theme.ToDoAppTheme
import com.singaludra.todoapp.utils.DateUtils
import com.singaludra.todoapp.utils.DateUtils.makeDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddScreen(
    navController: NavController, viewModel: SharedViewModel? = null
) {
    val scaffoldState = rememberScaffoldState()
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
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            val localFocusManager = LocalFocusManager.current
            val selectedDate = remember {
                mutableStateOf(Date())
            }
            val mDatePickerDialog =
                makeDatePicker(selectedDate, LocalContext.current, isMinDateToday = true)
            val focusRequester = remember { FocusRequester() }
            var textState by remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = textState,
                textStyle = TextStyle.Default.copy(fontSize = 24.sp),
                onValueChange = { fieldValue -> textState = fieldValue },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                }),
                placeholder = { Text(text = "Enter new task", fontSize = 24.sp) },
                singleLine = true
            )
            var descriptionState by remember { mutableStateOf(TextFieldValue()) }
            val scroll = rememberScrollState(0)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                TextField(
                    value = descriptionState,
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                    onValueChange = { fieldValue -> descriptionState = fieldValue },
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scroll),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        localFocusManager.clearFocus()
                    }),
                    placeholder = {
                        Text(
                            text = "Description(optional)",
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val localDensity = LocalDensity.current
                var heightIs by remember {
                    mutableStateOf(0.dp)
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            mDatePickerDialog.show()
                        }
                        .onGloballyPositioned { coordinates ->
                            heightIs = with(localDensity) { coordinates.size.height.toDp() }
                        }
                        .border(0.5.dp, Color.LightGray, RoundedCornerShape(24.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = DateUtils.calculateDateText(selectedDate.value, isPossessiveForm = false),
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Button(
                onClick = {
                    viewModel?.addToDoEntry(
                        textState.text,
                        descriptionState.text,
                        selectedDate.value
                    )
                    navController.navigateUp()
                },
                shape = RoundedCornerShape(32.dp),
                enabled = textState.text.isNotEmpty(),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (textState.text.isNotEmpty()) MaterialTheme.colors.primary else Color.DarkGray
                )
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Save task")
                }
            }
        }
    })

}



@Preview(showBackground = true)
@Composable
fun previewAddScreen(){
    ToDoAppTheme {
        AddScreen(navController = rememberNavController())
    }
}