package com.goetu.go3timekeepingserver.navigationDrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.goetu.go3timekeepingserver.navigation_drawer.AppBar
import com.goetu.go3timekeepingserver.navigation_drawer.DrawerBody
import com.goetu.go3timekeepingserver.navigation_drawer.DrawerHeader
import com.goetu.go3timekeepingserver.navigation_drawer.MenuItem
import com.goetu.go3timekeepingserver.ui.theme.GoETUTimekeepingServerTheme
import kotlinx.coroutines.launch

class NavigationDrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoETUTimekeepingServerTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            )
                        ) {
                            println("Clicked on ${it.title}")
                        }
                    },
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                )
            }
        }
    }
}