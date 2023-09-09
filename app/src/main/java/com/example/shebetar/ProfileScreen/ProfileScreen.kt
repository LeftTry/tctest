package com.example.shebetar.ProfileScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUserByDevice
import com.example.shebetar.R
import com.example.shebetar.TopNavBar.TopNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(scope: CoroutineScope, scaffoldState: ScaffoldState){
    var user = User()
    runBlocking{launch{user = getUserByDevice()
        }}
    Log.d("Profile", user.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar(scope = scope, scaffoldState = scaffoldState)
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)){
            Box(modifier = Modifier.padding(top = 5.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.basicavatar),
                    contentScale = ContentScale.Crop,
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }
            Column(modifier = Modifier.padding(start = 50.dp, end = 15.dp)
            .align(alignment = Alignment.CenterVertically)) {
                Text(text = user.firstName, fontSize = 18.sp, modifier = Modifier.padding(top = 20.dp))
                Text(text = "@${user.nickname}")
            }

            Text(text = user.lastName, fontSize = 18.sp, modifier = Modifier
                .padding(end = 15.dp)
                .align(alignment = Alignment.CenterVertically))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)){

        }
        Divider(color = androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface)
    }
    
}