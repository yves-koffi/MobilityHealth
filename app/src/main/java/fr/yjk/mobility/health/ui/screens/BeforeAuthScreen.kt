package fr.yjk.mobility.health.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun BeforeAuthScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .width(width = 90.dp)
                        .height(30.dp),
                    painter = painterResource(R.drawable.lgoo2),
                    contentDescription = "logo"
                )
                Box(modifier = modifier.weight(weight = 1f)) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.before),
                        contentDescription = "logo"
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Votre Assistance voyage\n simplifiée, où que vous alliez.",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Inscrivez-vous en quelques clics et bénéficiez d’une protection complète pour vos déplacements à l’étranger.",
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {}) {
                        Text("Se connecter", style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {}) {
                        Text("S’inscrire", style = MaterialTheme.typography.titleMedium)
                    }
                }

            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun BeforeAuthScreenPreview() {
    MobilityHealthTheme {
        BeforeAuthScreen()
    }
}