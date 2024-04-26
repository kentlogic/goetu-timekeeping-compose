package com.goetu.go3timekeepingserver.permissions

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PermissionMainViewModel: ViewModel() {

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if(!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}