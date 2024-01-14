package com.example.shebetar.RegisterLoginScreen

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.addUser
import com.example.shebetar.DataBase.loginDevice
import com.example.shebetar.TopNavBar.TopNavBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import java.util.Date

@Composable
fun RegisterComponent(navController: NavHostController, context: Context){
    //Initializing TextField vars
    val username = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatedPassword = remember { mutableStateOf("") }
    val dateOfBirth = remember { mutableStateOf("Select date") }

    //Variables for interface
    val padding = 5.dp
    val topPadding = 10.dp

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear:  Int
    val mMonth: Int
    val mDay:   Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear   = mCalendar.get(Calendar.YEAR)
    mMonth  = mCalendar.get(Calendar.MONTH)
    mDay    = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            mDate.value = "$dayOfMonth.${month+1}.$year"
        }, mYear, mMonth, mDay
    )
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopNavBar()
        OutlinedTextField(value = username.value,
            onValueChange = {newUsername -> username.value = newUsername},
            label = { Text(text = "Username")},
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = topPadding, bottom = padding)
            .onFocusChanged {
                scope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            })
        OutlinedTextField(value = firstName.value,
            onValueChange = {newFirstName -> firstName.value = newFirstName},
            label = { Text(text = "First name")},
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(padding)
            .onFocusChanged {
                scope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            })
        OutlinedTextField(value = lastName.value,
            onValueChange = {newLastName -> lastName.value = newLastName},
            label = { Text(text = "Last name")},
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(padding)
            .onFocusChanged {
                scope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            })
        OutlinedTextField(value = email.value,
            onValueChange = {newEmail -> email.value = newEmail},
            label = { Text(text = "Email")},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(padding)
                .onFocusChanged {
                    scope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                })
        OutlinedTextField(value = phone.value,
            onValueChange = {newPhone -> phone.value = newPhone},
            label = { Text(text = "Phone")},
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(padding)
            .onFocusChanged {
                scope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            })
        OutlinedTextField(value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {newPassword -> password.value = newPassword},
            label = { Text(text = "Password")},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(padding)
                .onFocusChanged {
                    scope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    scope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                }
            ),)
        OutlinedTextField(value = repeatedPassword.value,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {newRepeatedPassword -> repeatedPassword.value = newRepeatedPassword},
            label = { Text(text = "Repeat password")},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(padding)
                .onFocusChanged {
                    scope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                })
        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = {
            mDatePickerDialog.show()
            dateOfBirth.value = mDate.value
        }, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.inverseSurface),
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(200.dp) ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "DateOfBirth",
                    tint = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = dateOfBirth.value,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

        // Displaying the mDate value in the Text
        Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
        Button(onClick = {
                checkCorrectInput(firstName, lastName, username, phone, email,
                    password, repeatedPassword, context, navController, mDay, mMonth, mYear)
                         },
            ) {
            Text(text = "Submit")
        }
    }
}

fun checkCorrectInput(firstName: MutableState<String>, lastName: MutableState<String>, username: MutableState<String>,
                      phone: MutableState<String>, email: MutableState<String>, password: MutableState<String>,
                      repeatedPassword: MutableState<String>, context: Context, navController: NavHostController,
                      mDay: Int, mMonth: Int, mYear: Int){
    if(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$")
            .matches(email.value)) {
        if (password.value == repeatedPassword.value) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, mYear)
            calendar.set(Calendar.MONTH, mMonth)
            calendar.set(Calendar.DAY_OF_MONTH, mDay)
            val date = calendar.time
            var user = User()
            runBlocking {
                launch(Dispatchers.IO) {
                    user = User(
                        firstName.value,
                        lastName.value,
                        username.value,
                        email.value,
                        phone.value,
                        password.value,
                        date
                    )
                    addUser(user, context)
                }
            }
            loginDevice(user)
            navController.navigate("home")
        } else {
            val toast =
                Toast.makeText(context, "Passwords didn't match", Toast.LENGTH_SHORT)
            toast.show()
            Log.d("Tag", "${password.value} == ${repeatedPassword.value}")
        }
    } else {
        val toast =
            Toast.makeText(context, "Email is incorrect", Toast.LENGTH_SHORT)
        toast.show()
    }
}
