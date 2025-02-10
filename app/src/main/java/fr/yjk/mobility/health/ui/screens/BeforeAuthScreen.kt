package fr.yjk.mobility.health.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.CustomOutlineButton
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol

@Composable
fun BeforeAuthScreen(onLogin:()->Unit,onRegister:()->Unit) {
    BackHandler {

    }
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
                Box(modifier = Modifier.weight(weight = 1f)) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.before),
                        contentDescription = "logo"
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        stringResource(R.string.beforeAuthTitle),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = handelGotDBol,
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        stringResource(R.string.beforeAuthInfo),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 13.sp,
                            lineHeight = 16.sp,
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CustomButton(text = stringResource(R.string.login)) {
                            onLogin()
                        }
                        CustomOutlineButton(text = stringResource(R.string.register)) {
                            onRegister()
                        }
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
        BeforeAuthScreen(onLogin = {}, onRegister = {})
    }
}