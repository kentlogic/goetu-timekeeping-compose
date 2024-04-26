package com.goetu.go3timekeepingserver.permissions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onOpenAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = onDismiss,
        buttons = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant Permission"
                    } else {
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onOpenAppSettingsClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
                )

            }
        }, title = {
Text(text = "${permissionTextProvider.getDescription(isPermanentlyDeclined = isPermanentlyDeclined)} Required")
        }, text = {
Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined = isPermanentlyDeclined))
        },
        modifier = modifier
    )

}

interface PermissionTextProvider {
   fun getDescription(isPermanentlyDeclined: Boolean): String
}

class ReadPhoneStatePermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "You permanently declined the Read Phone State permission. "+
                    "Go to the app settings to grant it"
        } else {
            "Read Phone State permission is required for security purposes"
        }
    }
}

class InternetPermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "You permanently declined the Internet permission. "+
                    "Go to the app settings to grant it"
        } else {
            "Internet permission is required to get the latest updates"
        }
    }
}