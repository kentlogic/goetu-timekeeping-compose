package com.goetu.go3timekeepingserver

import DateTimeLabel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goetu.go3timekeepingserver.destinations.AboutScreenDestination
import com.goetu.go3timekeepingserver.timelog.presentation.PinCodeEvent
import com.goetu.go3timekeepingserver.timelog.presentation.PinCodeViewModel
import com.goetu.go3timekeepingserver.timelog.presentation.TimelogEvent
import com.goetu.go3timekeepingserver.timelog.presentation.TimelogViewModel
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun TopMenuBar(navigator: DestinationsNavigator,    timelogViewModel: TimelogViewModel = hiltViewModel(),pinCodeViewModel: PinCodeViewModel = hiltViewModel()) {
    var ticks by remember { mutableStateOf(60) }
    LaunchedEffect(Unit) {
        while(true) {
            delay(1.seconds)
            if (ticks != 0)
            {
                ticks--
            } else {
                timelogViewModel.onEvent(TimelogEvent.Refresh)
                pinCodeViewModel.onEvent(PinCodeEvent.Refresh)

                ticks = 60
            }
        }
    }
    TopAppBar(
        title = {DateTimeLabel() },
        actions = {
            Button(
                onClick = {                ticks = 60
                    timelogViewModel.onEvent(TimelogEvent.Refresh)
                    pinCodeViewModel.onEvent(PinCodeEvent.Refresh)
                          },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ),  colors = ButtonDefaults.buttonColors(
                    backgroundColor = primaryDark,
                    contentColor = Color.White)
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Refresh",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Pin and QR Code will Refresh in $ticks seconds")
            }
            Spacer(modifier = Modifier.width(11.dp))
            Button(
                onClick = {
                  //  navigator.navigate(AboutScreenDestination)
                },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ),  colors = ButtonDefaults.buttonColors(
                    backgroundColor = primaryDark,
                    contentColor = Color.White)
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "About",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("About")
            }
            Spacer(modifier = Modifier.width(11.dp))

                Button(
                    onClick = {
                     },
                    // Uses ButtonDefaults.ContentPadding by default
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),  colors = ButtonDefaults.buttonColors(
                        backgroundColor = primaryDark,
                        contentColor = Color.White)
                ) {
                     Icon(
                        Icons.Filled.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Logout")
                }
        }
    )
}