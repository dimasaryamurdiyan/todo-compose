package com.singaludra.todoapp.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.singaludra.todoapp.R

@Composable
fun TopBarMoreMenu(
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onDeleteCompletedClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
){
    TopBarIconButton(
        onClick = onExpand,
        icon = Icons.Default.MoreVert,
        contentDescription = R.string.cd_more_vertical
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.delete_all_completed)) },
            onClick = onDeleteCompletedClick,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete_all_completed)
                )
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.delete_all_tasks)) },
            onClick = onDeleteAllClick,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete_all_tasks)
                )
            }
        )
    }
}