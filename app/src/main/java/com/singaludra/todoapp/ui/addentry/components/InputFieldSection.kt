package com.singaludra.todoapp.ui.addentry.components

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.utils.DateUtils
import java.util.Date

@Composable
fun InputFieldSection (
    paddingValues: PaddingValues,
    textState: String,
    descriptionState: String,
    selectedDate: MutableState<Date>,
    toDo: ToDo? = null,
    scroll: ScrollState,
    focusRequester: FocusRequester,
    localFocusManager: FocusManager,
    mDatePickerDialog: DatePickerDialog,
    onGloballyPositionedChange: (LayoutCoordinates) -> Unit,
    onValueChange: (String) -> Unit,
    onValueDescriptionChange: (String) -> Unit,
    updateTodo: () -> Unit,
    navigateBack: () -> Unit,
    isUpdateData: Boolean = false
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        TextField(
            value = textState,
            textStyle = TextStyle.Default.copy(fontSize = 24.sp),
            onValueChange = onValueChange,
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
                onValueChange = onValueDescriptionChange,
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
            Box(
                modifier = Modifier
                    .clickable {
                        mDatePickerDialog.show()
                    }
                    .onGloballyPositioned { coordinates ->
                        onGloballyPositionedChange(coordinates)
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
                updateTodo()
                navigateBack()
            },
            shape = RoundedCornerShape(32.dp),
            enabled = textState.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.End)
                .padding(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (textState.isNotEmpty()) MaterialTheme.colors.primary else Color.DarkGray
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
}