package com.example.shebetar.ProfileScreen

import android.graphics.Picture
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUser
import com.example.shebetar.TopNavBar.TopNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(scope: CoroutineScope, scaffoldState: ScaffoldState){
    var user = User()
    runBlocking{launch{user = getUser()
        }}
    Log.d("Profile", user.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar(scope = scope, scaffoldState = scaffoldState)
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)){
            Image(painter = , contentDescription = "avatar",  modifier = Modifier
                .size(128.dp)
                .clip(CircleShape))
            Text(text = user.firstName, modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.CenterVertically))
            Text(text = user.lastName, modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.CenterVertically))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)){
            Text(text = "@${user.nickname}")
        }
        Divider(color = androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface)
    }
    
}