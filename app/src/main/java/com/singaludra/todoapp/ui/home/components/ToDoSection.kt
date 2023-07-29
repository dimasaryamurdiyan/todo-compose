package com.singaludra.todoapp.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.delay
import java.util.Date
import kotlin.math.abs

@Composable
fun ToDoSection(
    todo: ToDo,
    onClick: (todo: ToDo) -> Unit,
    onDelete: (todo: ToDo) -> Unit,
    modifier: Modifier = Modifier
) {
    var cardWith by remember {
        mutableStateOf(0f)
    }

    var isDragged by remember {
        mutableStateOf(false)
    }

    val isCheck = todo.isDone

    var offsetX by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(key1 = isDragged) {
        if (isDragged) {
            delay(1500)
            onDelete(todo)
        }
    }

    if (isDragged)
        Row (
            modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.DarkGray
            )
            Text(text = "The task was deleted", color = Color.Gray)
            OutlinedButton(
                onClick = {
                    isDragged = false
                    offsetX = 0f
                },
                border = BorderStroke(0.5.dp, Color.Gray),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text(text = "UNDO", color = Color.Gray, modifier = Modifier.padding(2.dp))
            }
        }
    else {
        Card(
            modifier = modifier
                .onGloballyPositioned {
                    cardWith = (it.size.width.toFloat())
                }
                .offset { IntOffset(offsetX.toInt(), 0) }
                .pointerInput(Unit) {
                    detectDragGestures(onDrag = { change, amount ->
                        change.consume()
                        val (x) = amount
                        if (x < 0) {
                            offsetX += amount.x
                        }
                    }, onDragEnd = {
                        if (abs(offsetX) > 0.5f * cardWith)
                            isDragged = true
                        else
                            offsetX = 0f
                    })
                },
            shape = RoundedCornerShape(12.dp),
            elevation = 2.dp
        ) {
            var isExpanded by remember {
                mutableStateOf(false)
            }
            val rotateState by animateFloatAsState(
                targetValue = if (isExpanded) 180F else 0F,
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                if (isCheck) Color.LightGray else Color.Transparent,
                                CircleShape
                            )
                            .border(
                                if (isCheck) BorderStroke(
                                    0.dp,
                                    Color.Transparent
                                ) else BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant), CircleShape
                            )
                            .clickable {
                                onClick(todo)
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (isCheck)
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        fontSize = 16.sp,
                        text = todo.title,
                        textDecoration = if (isCheck) TextDecoration.LineThrough else TextDecoration.None
                    )
                    if (todo.desc.isNotEmpty()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier
                                .clickable { isExpanded = !isExpanded }
                                .rotate(rotateState),
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                }
                AnimatedVisibility(visible = isExpanded) {
                    Text(
                        text = todo.desc,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoSectionPreview() {
    ToDoAppTheme {
        ToDoSection(
            todo = ToDo(1, "Tes", "Ini Deskripsi", Date("1/1/2023")),
            onClick = {},
            onDelete = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}