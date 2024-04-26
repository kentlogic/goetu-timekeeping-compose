//package com.goetu.go3timekeepingserver
//
//import android.Manifest
//import android.app.Activity
//import android.app.Instrumentation.ActivityResult
//import android.content.Intent
//import android.icu.text.SimpleDateFormat
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.provider.Settings
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.ui.Modifier
//
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.goetu.go3timekeepingserver.destinations.PostScreenDestination
//import com.goetu.go3timekeepingserver.destinations.ProfileScreenDestination
//import com.goetu.go3timekeepingserver.permissions.InternetPermissionTextProvider
//import com.goetu.go3timekeepingserver.permissions.PermissionDialog
//import com.goetu.go3timekeepingserver.permissions.PermissionMainViewModel
//import com.goetu.go3timekeepingserver.permissions.ReadPhoneStatePermissionTextProvider
//
//
//import com.goetu.go3timekeepingserver.ui.theme.GoETUTimekeepingServerTheme
//import com.google.accompanist.systemuicontroller.SystemUiController
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//
//import com.ramcosta.composedestinations.annotation.Destination
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//import java.util.*
//
//import com.plcoding.ktorclientandroid.data.remote.PostsService
//import com.plcoding.ktorclientandroid.data.remote.dto.PostRequest
//import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
//import com.ramcosta.composedestinations.DestinationsNavHost
//import kotlinx.coroutines.delay
//import kotlin.time.seconds
//
//class MainActivity : ComponentActivity() {
//    private val permissionsToRequest = arrayOf(
//        Manifest.permission.RECORD_AUDIO,
//        Manifest.permission.CALL_PHONE,
//    )
//     override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val systemUiController = rememberSystemUiController()
//            val permissionViewModel = viewModel<PermissionMainViewModel>()
//            val permissionDialogQueue = permissionViewModel.visiblePermissionDialogQueue
//
//            val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestPermission(),
//                onResult = {isGranted ->
//                    permissionViewModel.onPermissionResult(
//                        permission = Manifest.permission.INTERNET,
//                        isGranted = isGranted
//                    )
//                }
//            )
//
//            val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestMultiplePermissions(),
//                onResult = {permissions ->
//                    permissionsToRequest.forEach{ permission->
//                        permissionViewModel.onPermissionResult(
//                            permission = permission,
//                            isGranted = permissions[permission] == true
//                        )
//                    }
//
//                }
//            )
//
//            SideEffect {
//                systemUiController.isStatusBarVisible = false // Status bar
//                systemUiController.isNavigationBarVisible = false // Navigation bar
//                systemUiController.isSystemBarsVisible = false // Status & Navigation bars
//
//            }
//            permissionDialogQueue.forEach{permission->
//                PermissionDialog(
//                    permissionTextProvider = when (permission) {
//                        Manifest.permission.INTERNET -> {
//                            InternetPermissionTextProvider()
//                        }
//                        Manifest.permission.READ_PHONE_STATE -> {
//                            ReadPhoneStatePermissionTextProvider()
//                        } else -> return@forEach
//                    },
//                    isPermanentlyDeclined =!shouldShowRequestPermissionRationale(permission),
//                    onDismiss = permissionViewModel::dismissDialog,
//                    onOkClick = {
//                        permissionViewModel.dismissDialog()
//                        multiplePermissionResultLauncher.launch(arrayOf(permission))
//                    },
//                    onOpenAppSettingsClick = { ::openAppSettings})}
//
//            DestinationsNavHost(navGraph = NavGraphs.root)
//
//        }
//    }
//
//
//}
//fun Activity.openAppSettings() {
//    Intent(
//        Settings.ACTION_APPLICATION_SETTINGS,
//        Uri.fromParts("package", packageName, null)
//    ).also(::startActivity)
//}
////fun Activity.openAppSettings() {
////    Intent(
////        Settings.ACTION_APPLICATION_SETTINGS,
////        Uri.fromParts("package", packageName, null)
////    ).also(::startActivity)
////}
//
//
//@Composable
//fun TimelogItem(post: PostRequest) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(text = post.title, fontSize = 20.sp)
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(text = post.body, fontSize = 14.sp)
//    }
//
//}
//
//
//@Composable
//fun PostItem(
//    post: PostRequest,
//    onSelected: (PostRequest) -> Unit
//) {
//    Column(
//        modifier = Modifier.clickable {
//            onSelected(post) // selection callback
//        }
//    ) {
//        Text(text = post.title)
//        Text(text = post.body)
////        if(post.) {
////
////            Box(modifier = Modifier
////                .fillMaxWidth()
////                .height(100.dp)
////                .background(Color.Red)) {
////            }
////        }
//    }
//}
//
//
//@Destination
//@Composable
//fun ProfileScreen(
//    navigator: DestinationsNavigator,
//    user: User
//) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Profile Screen: $user", textAlign = TextAlign.Center)
//        Button(onClick = {
//            navigator.navigate(PostScreenDestination())
//        }) {
//            Text("Go to Post Screen")
//        }
//    }
//}
//
//@Destination
//@Composable
//fun PostScreen(
//    showOnlyPostsByUser: Boolean = false
//) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Post Screen, $showOnlyPostsByUser")
//    }
//}
//
//
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        // Turn off the decor fitting system windows, which means we need to through handling
////        // insets
////        setContent {
////
////
////Navigation()
////
////
////            }
////        }
////    }
////// GoETUTimekeepingServerTheme {
////// A surface container using the 'background' color from the theme
////
////
//////val painter = painterResource(id = R.drawable.img_kate)
//////val name = "Jose Rizal"
//////val designation = "Software Developer"
//////val time = "11:11"
//////val timelogType = "TIME-IN"
//////
//////Box(modifier = Modifier
//////.fillMaxWidth(0.5f)
//////.padding(16.dp)) {
//////    ImageCard(
//////        painter = painter,
//////        name = name,
//////        designation = designation,
//////        time = time,
//////        timelogType = timelogType
//////    )
//////  }
////
////
////    @Composable
////    fun ImageCard(
////        painter: Painter,
////        name: String,
////        designation: String,
////        timelogType: String,
////        time: String,
////        modifier: Modifier = Modifier
////    ) {
////        Card(
////            modifier = modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(15.dp),
////            elevation = 7.dp
////        ) {
////            Box(modifier = Modifier.height(300.dp)) {
////                Image(
////                    painter = painter,
////                    contentDescription = name,
////                    contentScale = ContentScale.FillBounds
////                )
////                Box(
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .background(
////                            Brush.verticalGradient(
////                                colors = listOf(
////                                    Color.Transparent,
////                                    Color.Gray,
////                                    Color.Black
////                                ),
////                                startY = 100f
////                            )
////                        )
////                )
////
////                Box(
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .padding(12.dp),
////                    contentAlignment = Alignment.BottomStart
////                ) {
////                    Spacer(modifier = Modifier.height(50.dp))
////
////                    Column( ) {
////                        Text(name, style = TextStyle(color = Color.White, fontSize = 16.sp))
////                        Text(designation, style = TextStyle(color = Color.White, fontSize = 16.sp))
////                        Text(timelogType, style = TextStyle(color = Color.White, fontSize = 16.sp))
////                        Text(time, style = TextStyle(color = Color.White, fontSize = 16.sp))
////
////                    }
////                   }
////
////            }
////
////        }
////
////    }
////
////
////
