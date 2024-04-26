package com.goetu.go3timekeepingserver


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.goetu.go3timekeepingserver.destinations.MainScreenDestination
import com.goetu.go3timekeepingserver.timelog.presentation.AuthEvent
import com.goetu.go3timekeepingserver.timelog.presentation.AuthViewModel
import com.goetu.go3timekeepingserver.timelog.presentation.TimelogViewModel
import com.goetu.go3timekeepingserver.ui.theme.GoETUTimekeepingServerTheme
import com.goetu.go3timekeepingserver.ui.theme.backgroundDark
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import com.goetu.go3timekeepingserver.util.isValidEmail
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnusedBoxWithConstraintsScope")
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel  = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()

    var emailTextFieldState by remember {
        mutableStateOf("")
    }
    var passwordTextFieldState by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var invalidEmail by remember {
        mutableStateOf(false)
    }
    var invalidPassword by remember {
        mutableStateOf(false)
    }

    val authState = authViewModel.state



    var showLoadingDialog by remember { mutableStateOf(false) }

    val mContext = LocalContext.current

    SideEffect {
        systemUiController.isStatusBarVisible = false // Status bar
        systemUiController.isNavigationBarVisible = false // Navigation bar
        systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    }
    GoETUTimekeepingServerTheme {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundDark)
                .padding(11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(2f)) {
                Image(
                    painter = painterResource(id = R.drawable.img_hris_logo_text_blue),
                    contentDescription = "HRIS logo",
                    contentScale = ContentScale.FillWidth,
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()  //fill the max height
                    .width(1.5.dp)
                    .absolutePadding(left = 3.dp, right = 3.dp, top = 11.dp, bottom = 11.dp)
            )
            BoxWithConstraints(modifier = Modifier.weight(1.5f)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth().absolutePadding(right = 25.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Email", color = Color.Gray) },
                        singleLine = true,
                        shape = RoundedCornerShape(11.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Email,
                                contentDescription = "Email Icon",
                                tint = Color.DarkGray
                            )
                        },
                        //label = { Text("Email") },
                        value = emailTextFieldState,
                        onValueChange = {
                            emailTextFieldState = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(left = 16.dp, bottom = 7.dp)
                            .clip(shape = RoundedCornerShape(11.dp)),
                        //.border(BorderStroke(2.dp, color = Color.Gray),
                        textStyle = TextStyle(fontSize = 19.sp, color = Color.Gray),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            backgroundColor = White,
                            placeholderColor = Color.Gray,
                        )
                    )
                    if (invalidEmail) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .absolutePadding(left = 16.dp),
                            text = "Invalid Email",
                            color = Color.Red
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(11.dp))

                    TextField(
                        placeholder = { Text(text = "Password", color = Color.Gray) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        isError =invalidPassword,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Lock,
                                contentDescription = "Email Icon",
                                tint = Color.DarkGray
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    imageVector = if (showPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                                    contentDescription = if (showPassword) "Show Password" else "Hide Password",
                                    tint = Color.DarkGray
                                )
                            }
                        },
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        //label = { Text("Password") },
                        value = passwordTextFieldState,
                        onValueChange = {
                            passwordTextFieldState = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                            .absolutePadding(left = 16.dp, bottom = 7.dp)
                            .clip(shape = RoundedCornerShape(11.dp)),
                        textStyle = TextStyle(fontSize = 19.sp, color = Color.Gray),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            backgroundColor = White,
                            placeholderColor = Color.Gray,
                        )
                    )
                    if (invalidPassword) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .absolutePadding(left = 16.dp),
                            text = "Invalid Password",
                            color = Color.Red
                        )
                    }


                        Button(
                            enabled = !authState.isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .absolutePadding(top = 16.dp, left = 16.dp),
                            onClick = {
                                if (passwordTextFieldState.isEmpty()){
                                    Toast.makeText(mContext, "Password is Empty", Toast.LENGTH_SHORT).show()
                                    invalidPassword = true
                                } else {
                                    invalidPassword = false
                                }

                                if(emailTextFieldState.isEmpty() or !isValidEmail(emailTextFieldState)){
                                    Toast.makeText(mContext, "Invalid Email", Toast.LENGTH_SHORT).show()
                                    invalidEmail = true
                                } else {
                                    invalidEmail = false

                                }
                                if(emailTextFieldState.isNotEmpty() and passwordTextFieldState.isNotEmpty() and isValidEmail(emailTextFieldState)){
                                    try {
                                        authViewModel.onEvent(AuthEvent.Login, emailTextFieldState, passwordTextFieldState)
                                    } catch (e: Exception) {
                                        Log.i("Sdsad", "aeaeae $e")
                                    }
                                }
                                // authViewModel.login(emailTextFieldState, passwordTextFieldState)
                                // navigator.navigate(MainScreenDestination())
                            },
                        ) {
                            if (authState.isLoading) {
                                CircularProgressIndicator(color = White)
                                Spacer(modifier = Modifier.width(5.dp))
                                //  showLoadingDialog = true
                            }
                            Text(
                                modifier = Modifier.padding(7.dp),
                                text = "Login",
                                color = White,fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }

                      if (authState.isLoaded) {
                         showLoadingDialog = false
                         navigator.navigate(MainScreenDestination())
                    } else if (authState.authError) {
                       // showLoadingDialog = false
                         Toast.makeText(mContext, "${authState.authErrorMessage}", Toast.LENGTH_SHORT).show()
                    }

                    if (showLoadingDialog) {
                        Dialog(
                            onDismissRequest = { showLoadingDialog = false },
                            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                        ) {
                            Box(
                                contentAlignment= Center,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(primaryDark, shape = RoundedCornerShape(8.dp))
                            ) {
                                CircularProgressIndicator(color = White)
                            }
                        }
                    }
                }
            }

        }

    }
}


//{
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Login Screen")
//        Button(onClick = {
//            navigator.navigate(
//                ProfileScreenDestination(
//                    User(
//                        name = "Chris P. Bacon",
//                        id = "userid",
//                        created = " LocalDateTime.now()"
//                    )
//                )
//            )
//        }) {
//            Text("Go to Profile Screen")
//        }
//    }
//}

