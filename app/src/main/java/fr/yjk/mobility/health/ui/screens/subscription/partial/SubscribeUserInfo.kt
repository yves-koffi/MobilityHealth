package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol

@Composable
fun SubscribeUserInfo() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState()),verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(255.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.product01),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Assistance voyage")
                        Text("Prix en fcfa", style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            lineHeight = 18.sp
                        )
                        )
                    }
                    Spacer(modifier = Modifier.height(height = 16.dp))
                    TextButton(onClick = {}) {
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.nav),
                                contentDescription = null,
                                Modifier.size(size = 24.dp)
                            )
                            Text("Détails")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Questions de santé", style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                fontFamily = handelGotDBol,
                letterSpacing = 0.15.sp
            )
        )
        for (i in 1..4)
            CustomTextField(value = "",
                onValueChange = { },
                label = "Question $i",
                placeholder = "Question santé",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                })
    }
}


@Preview
@Composable
private fun SubscribeUserInfoPreview() {
    MobilityHealthTheme {
        SubscribeUserInfo()
    }
}