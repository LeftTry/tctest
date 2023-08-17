package com.example.shebetar.TopNavBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopNavBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        IconButton(
            onClick = { scope.launch{ scaffoldState.drawerState.open() } }) {
            Icon(imageVector = Icons.Default.List, contentDescription = "LeftSideMenu",
                tint = MaterialTheme.colorScheme.surface)
        }
    }
}

@Composable
fun TopNavBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
    }
}