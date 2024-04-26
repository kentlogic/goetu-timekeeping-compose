package com.goetu.go3timekeepingserver.timelog.presentation

import TimelogCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun TimelogListScreen(
   timelogs: List<Timelog>
) {
//    val state = viewModel.state
//    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
//    viewModel.onEvent(TimelogEvent.Init)

//    SwipeRefresh(
//        state = swipeRefreshState,
//        onRefresh = { viewModel.onEvent(TimelogEvent.Refresh) }) {

        Column(modifier = Modifier.fillMaxSize()) {
            if (timelogs.isNotEmpty() && timelogs.size >= 2) {
              val  timelogList = timelogs.subList(1, timelogs.size -1)
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(timelogList.size) { i ->
                        val timelog = timelogList[i]
                        Box(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(11.dp)
                        ) {
                            TimelogCard(
                                timelog = timelog,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // TODO: Display details
                                    }
                                    .padding(16.dp)
                            )
                        }
                        if (i < timelogs.size) {
                            Divider(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }
                    }
                }

            } else if ( timelogs.isEmpty() || timelogs.size == 1) {
                NoRecordsCard()
            }

        }


    //}

}