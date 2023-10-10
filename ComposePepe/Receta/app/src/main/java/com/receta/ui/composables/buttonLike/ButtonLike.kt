package com.receta.ui.composables.buttonLike

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receta.ui.theme.RecetaTheme

@Composable
fun ButtonLike(
    iLike:ButtonLikeUiState,
    modifier: Modifier? =Modifier,
    onILikePressed:()->Unit
){
    Row(
        modifier= modifier!!.then(
            Modifier
                .clip(RoundedCornerShape(40))
                .background(MaterialTheme.colorScheme.tertiary)
        ),
        verticalAlignment = Alignment.CenterVertically,
    ){
        IconButton(onClick = onILikePressed) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint=if(iLike.iLike) Color.Red else Color.Black
            )
        }
        Text(
            modifier=Modifier.padding(end=10.dp),
            text=iLike.numberOfLikes.toString(),
            color=Color.White
        )
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
        ButtonLike(
            iLike = iLikeState,
            modifier = Modifier.wrapContentSize(),
            onILikePressed = onILikePressed
        )
    }
}