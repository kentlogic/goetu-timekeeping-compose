import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerItemTile(icon: ImageVector,color: Color,  title: String, padding: Dp, action:()-> Unit) {
    TextButton(onClick =
       action
     ) {Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon, title, modifier = Modifier.size(30.dp), tint = color
        )
        Text(title,modifier = Modifier
            .absolutePadding(left = 16.dp), color = Color.DarkGray,
            fontSize = 18.sp    )
    }}
}