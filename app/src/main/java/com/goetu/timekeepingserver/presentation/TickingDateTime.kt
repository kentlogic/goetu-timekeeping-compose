import android.icu.text.SimpleDateFormat
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun DateTimeLabel() {
    val sdf = SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss aa")
    var currentDateTime by remember { mutableStateOf(sdf.format(Date())) }
    LaunchedEffect(Unit) {
        while(true) {
            delay(100)
            currentDateTime = sdf.format(Date())
        }
    }
    Text(text = currentDateTime)
}