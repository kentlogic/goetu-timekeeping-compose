//package com.goetu.go3timekeepingserver
//
//import DrawerItemTile
//import ListTile
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.net.Uri
//import android.provider.Settings
//import android.util.Log
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.*
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Home
//import androidx.compose.material.icons.rounded.Info
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.*
//import androidx.compose.ui.graphics.BlendMode.Companion.Screen
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//
//import androidx.core.view.WindowCompat
//import androidx.core.view.WindowInsetsControllerCompat
//
//import com.ramcosta.composedestinations.DestinationsNavHost
//
//@Composable
//fun MainWrapper(){
//    /// Hide systemBar
//    val activity = LocalContext.current as Activity
//    with(WindowCompat.getInsetsController(activity.window, activity.window.decorView)) {
//        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//    }
//
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(
//        scaffoldState = scaffoldState,
//         ) { paddingValues ->
//        Box(modifier = Modifier.padding(paddingValues)) {
//            DestinationsNavHost(navGraph = NavGraphs.root)
//        }
//    }
//}