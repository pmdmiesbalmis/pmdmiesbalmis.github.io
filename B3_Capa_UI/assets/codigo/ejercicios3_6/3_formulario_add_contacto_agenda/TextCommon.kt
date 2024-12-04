
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextNewAccount(onClick: () -> Unit, color: Color = MaterialTheme.colorScheme.primary) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("No tienes cuenta?")
        Spacer(Modifier.width(10.dp))
        Text(
            "Crear cuenta",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = true, onClick = onClick),
            textAlign = TextAlign.End,
            color = color
        )
    }
}


@Composable
fun TextWithLine(texto: String, color: Color) {
    /* val activity = (LocalContext.current as Activity)
     val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
     var tamañoLinea =
         Math.round(metrics.bounds.width() - (activity.resources.displayMetrics.density * texto.length)*10)/10

    */
    val tamañoLinea=53
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(tamañoLinea.dp, 1.dp)
                .fillMaxWidth()
                .background(color)
        )
        Text(" $texto ", color = color)
        Box(
            Modifier
                .size(tamañoLinea.dp, 1.dp)
                .fillMaxWidth()
                .background(color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextNewAccountTest() {
    Column() {
        TextNewAccount(onClick = {})
        TextWithLine(texto = "Dirección", color = Color.Magenta)

    }
}
