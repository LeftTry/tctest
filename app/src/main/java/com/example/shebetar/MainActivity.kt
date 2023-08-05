package com.example.shebetar

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shebetar.HomeScreen.HomeScreen
import com.example.shebetar.HomeScreen.createPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val activity = (LocalContext.current as? Activity)
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    HomeScreen(navController = navController, scope, scaffoldState)
                    Surface(color = MaterialTheme.colorScheme.background) {
                        Scaffold(
                            scaffoldState = scaffoldState,
                            drawerContent={
                                Text("Profile", fontSize = 28.sp, modifier = Modifier.clickable {})
                                Text("Settings", fontSize = 28.sp, modifier = Modifier.clickable {  })
                                Text("Exit", fontSize = 28.sp, modifier = Modifier.clickable { activity?.finish() })
                            },
                            bottomBar = {
                                BottomNavigationBar(navController = navController)
                            }, content = { padding ->
                                NavHostContainer(navController = navController, padding = padding, scope, scaffoldState)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search",
            tint = Color(0xFF0F9D58)
        )
        Text(text = "Search", color = Color.Black)
    }
}

@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notification",
            tint = Color(0xFF0F9D58)
        )
        Text(text = "Notifications", color = Color.Black)
    }
}

data class BottomNavItem(
    val label: String,
    val icon:  ImageVector,
    val route: String,
)

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Search",
            icon = Icons.Filled.Search,
            route = "search"
        ),
        BottomNavItem(
            label = "Notifications",
            icon = Icons.Filled.Notifications,
            route = "notifications"
        )
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                HomeScreen(navController, scope, scaffoldState)
            }
            composable("search") {
                SearchScreen()
            }
            composable("notifications") {
                NotificationScreen()
            }
            composable("postCreation"){
                PostCreationPage(navController)
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation()
        {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label, tint = MaterialTheme.colorScheme.surface)
                },
                label = {
                    Text(text = navItem.label, color = MaterialTheme.colorScheme.surface)
                },
                alwaysShowLabel = false
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreationPage(navController: NavHostController){
    val text = remember {mutableStateOf("")}
    IconButton(
        modifier = Modifier
            .background(shape = RoundedCornerShape(50), color = Color.Transparent)
            .padding(start = 10.dp, top = 10.dp),
        onClick = { navController.navigate("home")}) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close"
        )
    }
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Button(
                onClick = {
                    createPost(text.value)
                    navController.navigate("home")
                },
                modifier = Modifier
                    .background(shape = RoundedCornerShape(50), color = Color.Transparent)
                    .padding(end = 10.dp, top = 10.dp)
                    .align(alignment = Alignment.End),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background),
                shape = CircleShape
            ) {
                Text("Post", color = MaterialTheme.colorScheme.surface)

            }
        TextField(
            value = text.value,
            onValueChange = { newText -> text.value = newText },
            label = { Text("Enter post's text") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
        )
    }
}
