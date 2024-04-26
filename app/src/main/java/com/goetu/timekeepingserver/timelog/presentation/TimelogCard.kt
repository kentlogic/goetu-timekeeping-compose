import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.goetu.go3timekeepingserver.R
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import com.goetu.go3timekeepingserver.ui.theme.primaryDark
import com.goetu.go3timekeepingserver.util.TimeUtil

@Composable
fun TimelogCard(timelog: Timelog, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .background(primaryDark)


    ) {
        Row( modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${timelog.image_url}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.img_placeholder),
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape)   // add a border (optional)
                    .background(Color.White)
            )
            Column {
                Text(
                    "${timelog.first_name} ${timelog.last_name}", modifier = Modifier
                        .absolutePadding(left = 16.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 21.sp,
                    overflow = TextOverflow.Clip
                )
                Text(
                    "${timelog.designation_name}",
                    modifier = Modifier
                        .absolutePadding(left = 16.dp),
                    overflow = TextOverflow.Clip

                )
                Text(
                    "${timelog.time_in_time_out}",
                    modifier = Modifier
                        .absolutePadding(left = 16.dp),

                    )
                Text(
                    "${timelog.time?.let { TimeUtil().TimeFromDateTime(timelog.time) }}",
                    modifier = Modifier
                        .absolutePadding(left = 16.dp),

                    )
            }
        }
    }
}