package ui.images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url

@Composable
fun DogImage(url: String) {
    val painterResource = asyncPainterResource(data = Url(url))
    KamelImage(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        resource = painterResource,
        contentScale = ContentScale.Crop,
        contentDescription = "Dog image",
        onLoading = {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center  // <-- Centering here
            ) {
                CircularProgressIndicator(
                    Modifier.size(32.dp)
                )
            }
        },
        onFailure = {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center  // <-- Centering here
            ) {
                Text(
                    text = "Image failed to load",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                )
            }
        }
    )
}