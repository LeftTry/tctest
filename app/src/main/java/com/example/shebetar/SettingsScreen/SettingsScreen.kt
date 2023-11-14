package com.example.shebetar.SettingsScreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shebetar.SettingsScreen.SettingsFunctions.test1
import com.example.shebetar.TopNavBar.TopNavBar

@Composable
fun SettingsScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TopNavBar()
        SettingsItem(name = "test 1", ::test1)
    }
}

@Composable
fun SettingsItem(name: String, onClick: (Context) -> Unit){
    val checked by remember { mutableStateOf(true) }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ){
        Text(text = name, modifier = Modifier.padding(horizontal = 10.dp))
        Switch(checked = checked, onCheckedChange = { onClick }, modifier = Modifier.padding(horizontal = 10.dp))
    }
}