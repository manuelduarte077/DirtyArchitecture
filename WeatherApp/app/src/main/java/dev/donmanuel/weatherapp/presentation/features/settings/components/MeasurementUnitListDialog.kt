package dev.donmanuel.weatherapp.presentation.features.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.donmanuel.weatherapp.presentation.features.settings.model.MeasurementUnitListItem
import dev.donmanuel.weatherapp.R
import dev.donmanuel.weatherapp.core.utils.Constants

@Composable
fun MeasurementUnitListDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val data = listOf(
        MeasurementUnitListItem(
            unit = Constants.IMPERIAL_MEASUREMENT_UNIT,
            description = stringResource(id = R.string.imperial_fahrenheit_f)
        ),
        MeasurementUnitListItem(
            unit = Constants.METRIC_MEASUREMENT_UNIT,
            description = stringResource(id = R.string.metric_celsius_c)
        )
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.chose_unit)) },
        text = {
            LazyColumn {
                items(data) { item ->
                    Text(
                        text = item.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onConfirm(item.unit) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.close),
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    MeasurementUnitListDialog(
        onConfirm = {},
        onDismiss = {}
    )
}
