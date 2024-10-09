package com.pmdm.proyectobase.ui.features.tema33

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.R
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

@Composable
private fun TarjetaBalmis(modifier: Modifier = Modifier) = ElevatedCard(
    modifier = modifier.then(Modifier.wrapContentSize()),
    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
) {
    Column {
        Surface(
            modifier = Modifier.clip(CardDefaults.shape),
            color = MaterialTheme.colorScheme.primary
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.balmis),
                contentDescription = "IES Doctor Balmis",
                contentScale = ContentScale.FillWidth,
                alpha = 0.8f
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = "IES Doctor Balmis",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = "Alicante",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = "Instituto de Educación Secundaria donde se imparte el Ciclo Formativo"
                    + " de Grado Superior de Desarrollo de Aplicaciones Multiplataforma",
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { }) {
                Text(text = "Saber más")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TarjetaBalmisPreview() {
    ProyectoBaseTheme {
        Surface {
            Box(Modifier.padding(16.dp)) {
                TarjetaBalmis()
            }
        }
    }
}