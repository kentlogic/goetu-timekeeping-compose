package com.goetu.go3timekeepingserver.timelog.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goetu.go3timekeepingserver.ui.theme.primaryDark

@Composable
fun NoRecordsCard() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(11.dp)
            .clip(shape = RoundedCornerShape(16.dp)).background(primaryDark),
    ) {
        Box(

            ) {
            Text(
                "No Records to show",
                Modifier.padding(25.dp),
                textAlign = TextAlign.Center,
                fontSize = 25.sp

            )
        }
    }
}