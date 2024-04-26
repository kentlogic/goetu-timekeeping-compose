import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListTile(icon: ImageVector,color: Color,  title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon, title, modifier = Modifier.size(30.dp), tint = color
         )
        Column {
            Text(title,modifier = Modifier
                .absolutePadding(left = 16.dp), color = Color.LightGray,
                fontSize = 18.sp    )
            Text(subtitle,modifier = Modifier
                .absolutePadding(left = 16.dp), color = Color.LightGray,)
        }
    }
}