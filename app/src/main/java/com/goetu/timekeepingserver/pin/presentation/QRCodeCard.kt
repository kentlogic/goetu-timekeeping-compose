package com.goetu.go3timekeepingserver.timelog.presentation

 import QRDialog
 import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material.Button
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.unit.dp
 import com.goetu.go3timekeepingserver.ui.theme.primaryDark
 import com.lightspark.composeqr.DotShape
 import com.lightspark.composeqr.QrCodeColors
 import com.lightspark.composeqr.QrCodeView


@Composable
fun QRCodeCard(
    code: String?,

 ) {
    val openDialog = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .clip(shape = RoundedCornerShape(16.dp)).background(primaryDark)
        ) {
        Button(onClick = {
            openDialog.value = true
        }
        ) {
            if (openDialog.value) {
                QRDialog(openDialog = openDialog, code = code.orEmpty())
            } else {
                QrCodeView(
                    colors = QrCodeColors(
                        background = primaryDark,
                        foreground = Color.White
                    ),
                    dotShape = DotShape.Circle,
                    data = code.orEmpty(),
                    modifier = Modifier
                        .height(300.dp).width(300.dp)
                        .background(Color.White)
                )}
        }
    }


}