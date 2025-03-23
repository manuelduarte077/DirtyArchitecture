package dev.donmanuel.weatherapp.presentation.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.donmanuel.weatherapp.core.utils.ApiUtil
import dev.donmanuel.weatherapp.core.utils.TemperatureFormatter
import dev.donmanuel.weatherapp.domain.model.WeatherItem

@Composable
fun WeatherStatusWidget(
    modifier: Modifier = Modifier,
    weather: WeatherItem,
    isImperial: Boolean,
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .size(200.dp),
        shape = CircleShape,
        color = Color(0xFF87CEEB)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(ApiUtil.buildIconUrl(weather.weatherObject.icon)),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
            )

            Text(
                text = TemperatureFormatter.format(weather.temp.day, isImperial),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = weather.weatherObject.condition,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WeatherStatusWidget(
        weather = WeatherItem.createToPreview(),
        isImperial = false
    )
}
