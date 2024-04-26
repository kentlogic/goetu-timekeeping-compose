package com.goetu.go3timekeepingserver


import LastTimelogCard
import android.Manifest
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goetu.go3timekeepingserver.timelog.presentation.*
import com.goetu.go3timekeepingserver.ui.theme.backgroundDark
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

private val permissionsToRequest = arrayOf(
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.CALL_PHONE,
)


@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    timelogViewModel: TimelogViewModel = hiltViewModel(),
            pinCodeViewModel: PinCodeViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val timelogState = timelogViewModel.state
    val pinCodeState = pinCodeViewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = timelogState.isLoading)
    LaunchedEffect(Unit) {
        while (true) {
            timelogViewModel.onEvent(TimelogEvent.Init)
            delay(16000)
        }
    }

    LaunchedEffect(Unit) {
        pinCodeViewModel.onEvent(PinCodeEvent.Init)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopMenuBar(navigator)
        },
        content = { paddingValues ->
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { timelogViewModel.onEvent(TimelogEvent.Refresh) }) {
                Row(modifier = Modifier.padding(paddingValues)) {
                    Column(
                        modifier = Modifier
                            .weight(2.0f)
                            .padding(25.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!timelogState.isLoading && timelogState.timelogs.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .border(
                                        BorderStroke(2.dp, Color.Red),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .background(primaryDark)
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    AnimatedVisibility(visible = timelogState.isLoading) {
                                        CircularProgressIndicator()
                                    }
                                }
                                LastTimelogCard(timelogState.timelogs[0])
                            }
                        }
                        Spacer(modifier = Modifier.height(11.dp))
                        Row(
                            modifier = Modifier
                                .weight(2.0f)
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(300.dp)
                                    .absolutePadding(top = 21.dp, )
                            ) {

                                PinCard(pinCodeState.pinCode?.let { it.pincode.toString() })

                            }
                            Divider(
                                color = Color.White,
                                modifier = Modifier
                                    .width(1.dp)
                                    .padding(16.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .absolutePadding(top = 11.dp,  left = 16.dp)


                            ) {
                                QRCodeCard(code = pinCodeState.pinCode?.let { it.pincode.toString() })
                            }
                        }
                    }
                    if (timelogState.isLoading) {
                        Row(verticalAlignment = Alignment.CenterVertically ) {
                            AnimatedVisibility(visible = true) {
                                CircularProgressIndicator()
                            }
                        }
                    } else {
                    Box(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxSize()
                        .clip(shape = RoundedCornerShape(16.dp))
                        .border(
                            BorderStroke(2.dp, Color.Red),
                            shape = RoundedCornerShape(16.dp)
                        )
                    ) {
                            TimelogListScreen(timelogState.timelogs)
                        }


                    }
                }
            }


        })
}
//    { paddingValues ->
//
//    }

//    permissionDialogQueue.forEach { permission ->
//        PermissionDialog(
//            permissionTextProvider = when (permission) {
//                Manifest.permission.INTERNET -> {
//                    InternetPermissionTextProvider()
//                }
//                Manifest.permission.READ_PHONE_STATE -> {
//                    ReadPhoneStatePermissionTextProvider()
//                }
//                else -> return@forEach
//            },
//            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(permission),
//            onDismiss = permissionViewModel::dismissDialog,
//            onOkClick = {
//                permissionViewModel.dismissDialog()
//                multiplePermissionResultLauncher.launch(arrayOf(permission))
//            },
//            onOpenAppSettingsClick = ::openAppSettings
//        )
//    }



