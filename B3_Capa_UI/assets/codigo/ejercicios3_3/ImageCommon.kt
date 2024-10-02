package com.pmdm.login.ui.features.login.components

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmdm.login.R


@Composable
fun CircularImageFromResource(
    idImageResource: Int, contentDescription: String, size: Dp = Dp(130f)
) {

    Image(
        painter = painterResource(idImageResource),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop, //Si la imagen no es cuadrada
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun ImageCommonTest() {
    CircularImageFromResource(idImageResource = R.drawable.login, contentDescription = "Logearse")
}
