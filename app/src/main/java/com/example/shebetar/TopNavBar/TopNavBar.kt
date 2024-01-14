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

val topNavBarHeight = 50.dp

@Composable
fun TopNavBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    val topNavBarColor = MaterialTheme.colorScheme.primary
    val leftSideMenuColor = MaterialTheme.colorScheme.surface
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(topNavBarHeight)
            .background(color = topNavBarColor)
    ) {
        IconButton(
            onClick = { scope.launch{ scaffoldState.drawerState.open() } }) {
            Icon(imageVector = Icons.Default.List, contentDescription = "LeftSideMenu",
                tint = leftSideMenuColor)
        }
    }
}

@Composable
fun TopNavBar(){
    val topNavBarColor = MaterialTheme.colorScheme.primary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(topNavBarHeight)
            .background(color = topNavBarColor)
    ) {
    }
}