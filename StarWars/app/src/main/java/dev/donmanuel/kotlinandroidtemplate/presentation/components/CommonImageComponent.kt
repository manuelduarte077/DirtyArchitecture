package dev.donmanuel.kotlinandroidtemplate.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.donmanuel.kotlinandroidtemplate.R

@Composable
fun CommonImageComponent(modifier: Modifier = Modifier, url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(url)
            .placeholder(R.drawable.ic_planet_placeholder)
            .error(R.drawable.ic_image_loading_failed)
            .build(),
        contentDescription = "planet image",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}