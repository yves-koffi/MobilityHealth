package fr.yjk.mobility.health.ui.screens.directory.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun MakeDirectoryLastStep() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        for (i in 1..5)
            CustomTextField(
                value = "",
                onValueChange = { },
                label = stringResource(R.string.qs),
                placeholder = stringResource(R.string.qs)
            )
    }
}


@Preview
@Composable
private fun MakeDirectoryLastStepPreview() {
    MobilityHealthTheme {
        MakeDirectoryLastStep()
    }
}