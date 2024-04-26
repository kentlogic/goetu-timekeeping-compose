import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.goetu.go3timekeepingserver.R
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import com.goetu.go3timekeepingserver.util.TimeUtil


@Composable
fun LastTimelogCard(
    timelog: Timelog
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.width(7.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(timelog.image_url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.img_placeholder),
                contentDescription = "Last time log image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)   // add a border (optional)

            )
            Spacer(modifier = Modifier.width(11.dp))
             Row(
                modifier = Modifier.fillMaxWidth(),            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    InvertedListTile(
                        "Name",
                        "${timelog.first_name} ${timelog.last_name}"
                    )
                    Spacer(modifier = Modifier.height(11.dp))

                    InvertedListTile(
                        "Designation",
                        "${timelog.designation_name}"
                    )

                }

                Column {
                    InvertedListTile("Type", "${timelog.time_in_time_out}")

                    Spacer(modifier = Modifier.height(11.dp))

                    InvertedListTile("Time", "${timelog.time?.let { TimeUtil().TimeFromDateTime(timelog.time) }}")


                }
                 Spacer(modifier = Modifier.width(7.dp))
             }
        }

    }


}

 