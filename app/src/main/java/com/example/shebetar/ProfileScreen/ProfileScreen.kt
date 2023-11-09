package com.example.shebetar.ProfileScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUserById
import com.example.shebetar.DataBase.readUserDataFromJson
import com.example.shebetar.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(context: Context){
    val user = User()
    runBlocking{launch{user.toUserFromDocumentSnapshot(getUserById(readUserDataFromJson("LoginedUser", context)?.id))
        }}
    Log.d("Profile", user.toString())
    /*
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar(scope = scope, scaffoldState = scaffoldState)

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.Magenta)){
            IconButton(onClick = { navController.navigate("home")}) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Search button",
                    tint = MaterialTheme.colorScheme.surface)
            }
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
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)){

            Column(modifier = Modifier
                .padding(start = 50.dp, end = 15.dp)
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

     */
    TwitterProfileScreen()
}

@Composable
fun ProfileScreen() {
    val bio by remember { mutableStateOf("Your bio goes here.") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.basicavatar), // Replace with your profile image
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Name",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "@yourhandle",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = bio,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileActions()

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray,
            thickness = 0.5.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        TweetList()
    }
}

@Composable
fun ProfileActions() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("123 Following", modifier = Modifier.padding(16.dp))
        Text("456 Followers", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun TweetList() {
    LazyColumn {
        items(10) { index ->
            TweetCard()
        }
    }
}

@Composable
fun TweetCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Username",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This is a sample tweet. #JetpackCompose",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun TwitterProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
            )
        },
        content = {
            Column (modifier = Modifier.padding(it)){
                ProfileScreen()
            }

        }
    )
}