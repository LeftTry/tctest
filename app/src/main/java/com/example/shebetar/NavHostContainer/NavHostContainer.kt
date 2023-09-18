package com.example.shebetar.NavHostContainer

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.CommentScreen.CommentScreen
import com.example.shebetar.HomeScreen.HomeScreen
import com.example.shebetar.NotificationsScreen.NotificationsScreen
import com.example.shebetar.PostCreationScreen.PostCreationScreen
import com.example.shebetar.ProfileScreen.ProfileScreen
import com.example.shebetar.RegisterLoginScreen.LoginComponent
import com.example.shebetar.RegisterLoginScreen.RegisterComponent
import com.example.shebetar.RegisterLoginScreen.RegisterLoginScreen
import com.example.shebetar.SearchScreen.SearchScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    start: String,
    context: Context,
    post: Post
) {
    NavHost(
        navController = navController,
        startDestination = start,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                HomeScreen(navController, scope, scaffoldState)
            }
            composable("search") {
                SearchScreen()
            }
            composable("notifications") {
                NotificationsScreen()
            }
            composable("postCreation"){
                PostCreationScreen(navController, context)
            }
            composable("register"){
                RegisterComponent(navController, context)
            }
            composable("login"){
                LoginComponent(navController)
            }
            composable("registerLogin"){
                RegisterLoginScreen(navController = navController)
            }
            composable("profile"){
                ProfileScreen(scope, scaffoldState)
            }
            composable("comments/{postId}"){
            navBackStackEntry ->
                CommentScreen(postId = navBackStackEntry.arguments?.getString("postId"))
            }
        })

}