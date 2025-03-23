package fr.yjk.mobility.health.ui.screens.question.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun MakeQuestionStart() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(space = 14.dp)
    ) {
        Column(
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Nous apprécions votre intérêt pour notre application !",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Il ne vous reste plus qu'une étape pour finaliser votre inscription.",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview
@Composable
private fun MakeMakeQuestionStartPreview() {
    MobilityHealthTheme {
        MakeQuestionStart()
    }
}