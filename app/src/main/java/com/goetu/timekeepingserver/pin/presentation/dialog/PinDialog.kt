import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun PinDialog(openDialog: MutableState<Boolean>, code: String) {
    Dialog(onDismissRequest = { openDialog.value = false }) {
        PinDialogUI(openDialogCustom = openDialog, code = code)
    }
}


@Composable
fun PinDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    code: String
) {
    LaunchedEffect(Unit) {
        delay(5.seconds)
        openDialogCustom.value = false    }
    Card(
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(primaryDark)
              ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                code,
                fontSize = 150.sp,
                textAlign =TextAlign.Center,
                modifier = Modifier
                    .width(500.dp)
                     .padding(10.dp)
                    .background(primaryDark)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.Red),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(modifier =Modifier.clickable{
                    openDialogCustom.value = false
                }) {

                    Text(
                        "Close",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(11.dp)
                    )
                }

            }
        }
    }
}


//package com.goetu.go3timekeepingserver.timelog.presentation.dialog
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.AlertDialog
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.material.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun PinDialog() {
//    val showDialog = remember { mutableStateOf(false) }
//    if (showDialog.value) {
//        alert(msg = "Hello, this is an alert dialog!",
//            showDialog = showDialog.value,
//            onDismiss = {showDialog.value = false})
//    }
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Click the button to show an alert!")
//            Button(
//                modifier = Modifier.padding(vertical = 24.dp),
//                onClick = {showDialog.value = true}
//            ) {
//                Text("Click")
//            }
//        }
//}
//
//@Composable
//fun alert(msg : String,
//          showDialog: Boolean,
//          onDismiss: () -> Unit) {
//    if (showDialog) {
//        AlertDialog(
//            title = {
//                Text(msg)
//            },
//            onDismissRequest = onDismiss,
//            confirmButton = {
//                TextButton(onClick = onDismiss ) {
//                    Text("Dismiss")
//                }
//            },
//            dismissButton = {}
//        )
//    }
//}