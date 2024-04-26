package com.goetu.go3timekeepingserver


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goetu.go3timekeepingserver.permissions.PermissionDialog
import com.goetu.go3timekeepingserver.permissions.PermissionMainViewModel
import com.goetu.go3timekeepingserver.permissions.ReadPhoneStatePermissionTextProvider
import com.goetu.go3timekeepingserver.ui.theme.GoETUTimekeepingServerTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val permissionsToRequest = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CALL_PHONE,
    )

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {
            GoETUTimekeepingServerTheme {
                val permissionViewModel = viewModel<PermissionMainViewModel>()
                val dialogQueue = permissionViewModel.visiblePermissionDialogQueue

                val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = { perms ->
                        permissionsToRequest.forEach { permission ->
                            permissionViewModel.onPermissionResult(
                                permission = permission,
                                isGranted = perms[permission] == true
                            )
                        }
                    }
                )

                SideEffect {
                    multiplePermissionResultLauncher.launch(permissionsToRequest)
                }


                /// Hide systemBar
                val activity = LocalContext.current as Activity
                with(WindowCompat.getInsetsController(activity.window, activity.window.decorView)) {
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }

                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        DestinationsNavHost(navGraph = NavGraphs.root)
                            }
                    }

                dialogQueue
                    .reversed()
                    .forEach { permission ->
                        PermissionDialog(
                            permissionTextProvider = when (permission) {
                                Manifest.permission.READ_PHONE_STATE -> {
                                    ReadPhoneStatePermissionTextProvider()
                                }

                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                permission
                            ),
                            onDismiss = permissionViewModel::dismissDialog,
                            onOkClick = {
                                permissionViewModel.dismissDialog()
                                multiplePermissionResultLauncher.launch(
                                    arrayOf(permission)
                                )
                            },
                            onOpenAppSettingsClick = ::openAppSettings)
                    }
                }

            }
        }



}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}
