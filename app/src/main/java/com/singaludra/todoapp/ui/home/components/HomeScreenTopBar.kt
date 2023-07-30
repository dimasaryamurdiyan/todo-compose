package com.singaludra.todoapp.ui.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.singaludra.todoapp.ui.common.TopBarMoreMenu
import com.singaludra.todoapp.utils.getAppName

@Composable
fun HomeScreenTopBar(
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onDeleteCompletedClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
){
    TopAppBar(
        title = {
            Text(
                text = LocalContext.current.getAppName()
            )
        },
        actions = {
            TopBarMoreMenu(
                expanded = expanded,
                onExpand = onExpand,
                onDismiss = onDismiss,
                onDeleteCompletedClick = onDeleteCompletedClick,
                onDeleteAllClick = onDeleteAllClick
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    )
}