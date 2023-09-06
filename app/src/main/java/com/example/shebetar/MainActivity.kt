package com.example.shebetar

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.shebetar.BottomNavBar.BottomNavigationBar
import com.example.shebetar.DataBase.isDeviceLogined
import com.example.shebetar.DataBase.logoutDevice
import com.example.shebetar.HomeScreen.HomeScreen
import com.example.shebetar.NavHostContainer.NavHostContainer
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
                        var isDeviceLogined = false
                    runBlocking{launch{
                        isDeviceLogined = isDeviceLogined() }}
                    Log.d("isDeviceLogined", isDeviceLogined.toString())
                        if (isDeviceLogined) {
                            HomeScreen(navController = navController, scope, scaffoldState)
                            Surface(color = MaterialTheme.colorScheme.background) {
                                Scaffold(
                                    scaffoldState = scaffoldState,
                                    drawerContent = {
                                        Text("Profile", fontSize = 28.sp, modifier = Modifier.clickable {
                                            navController.navigate("profile")
                                            scope.launch{ scaffoldState.drawerState.close() }
                                        })
                                        Text(
                                            "Settings",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable { })
                                        Text(
                                            text = "LogOut",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable {
                                                logoutDevice()
                                                navController.navigate("registerLogin")
                                                scope.launch{ scaffoldState.drawerState.close() }
                                            })
                                        Text(
                                            "Exit",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable { activity?.finish() })
                                                    },
                                            bottomBar = {
                                                BottomNavigationBar(navController = navController)
                                                        }, content = { padding ->
                                                        NavHostContainer(
                                                        navController   = navController,
                                                        padding         = padding,
                                                        scope,
                                                        scaffoldState,
                                                            start = "home"
                                                        )
                                                    }
                                        )
                                }
                        }
                        else{
                            Surface(color = MaterialTheme.colorScheme.background) {
                                Scaffold(
                                    scaffoldState = scaffoldState,
                                    drawerContent = {
                                        Text("Profile", fontSize = 28.sp, modifier = Modifier.clickable {
                                            navController.navigate("profile")
                                        })
                                        Text(
                                            "Settings",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable { })
                                        Text(
                                            text = "LogOut",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable {
                                                logoutDevice()
                                                navController.navigate("registerLogin")
                                            })
                                        Text(
                                            "Exit",
                                            fontSize = 28.sp,
                                            modifier = Modifier.clickable { activity?.finish() })
                                    },
                                    content = { padding ->
                                        NavHostContainer(
                                            navController   = navController,
                                            padding         = padding,
                                            scope,
                                            scaffoldState,
                                            start = "registerLogin"
                                        )
                                    }
                                )
                            }
                        }
                }
            }
        }
    }
}









