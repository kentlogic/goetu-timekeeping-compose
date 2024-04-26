package com.goetu.go3timekeepingserver.timelog.presentation


import PinDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
import com.goetu.go3timekeepingserver.ui.theme.primaryDark

@Composable
fun PinCard(pin: String? ="" ) {
    val openDialog = remember { mutableStateOf(false) }
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxSize()
             .clip(shape = RoundedCornerShape(16.dp))
            .background(primaryDark).clickable{
                openDialog.value = true
            }

    )

    {
        if (openDialog.value) {
            PinDialog(openDialog = openDialog, code = pin.orEmpty())
        } else {
            AnimatedVisibility(visible = pin == null) {
                CircularProgressIndicator()
            }
            Text(
                pin.orEmpty(),
                textAlign = TextAlign.Center,
                fontSize = 101.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }

    }

}