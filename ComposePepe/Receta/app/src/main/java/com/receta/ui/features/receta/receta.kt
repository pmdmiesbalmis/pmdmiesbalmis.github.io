package com.receta.ui.features.receta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receta.ui.composables.buttonLike.ButtonLike
import com.receta.ui.composables.buttonLike.ButtonLikeUiState
import com.receta.ui.theme.RecetaTheme

@Composable
fun Receta(
    iLike: ButtonLikeUiState,
    recipeName: String,
    recipeDesc:String,
    recipeChef:String,
    onILikePressed: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        tonalElevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .padding(20.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text =recipeName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = recipeDesc,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier= Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = recipeChef,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red
                )
                ButtonLike(
                    iLike = iLike,
                    onILikePressed = onILikePressed
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var iLikeState by remember {
        mutableStateOf(
            ButtonLikeUiState(
                numberOfLikes = 8,
                iLike = false
            )
        )
    }

    var onILikePressed = {
        iLikeState = iLikeState.copy(
            numberOfLikes = iLikeState.numberOfLikes +
                    if (!iLikeState.iLike) 1 else -1,
            iLike = !iLikeState.iLike
        )
    }

    RecetaTheme {
        Receta(
            iLike = iLikeState,
            recipeName="Magdalemas de la abuela",
            recipeDesc="Fabulosas magdalenas con pepitas de chocolate y un suave sabor a naranja",
            recipeChef="Carlos Arguiñano",
            //modifier = Modifier.wrapContentSize(),
            onILikePressed = onILikePressed
        )
    }
}
