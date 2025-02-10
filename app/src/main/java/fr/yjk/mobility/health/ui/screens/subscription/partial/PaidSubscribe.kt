package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.fake.PaymentMethod
import fr.yjk.mobility.health.ui.components.SelectField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme


@Composable
fun PaidSubscribe() {
    val context = LocalContext.current
    val paymentMethod = PaymentMethod.items
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(space = 14.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(size = 24.dp),
            shadowElevation = 8.dp
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(154.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.product01),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                        ) {
                            Text(
                                "produit d’assistance", style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                            Spacer(modifier = Modifier.height(height = 16.dp))
                            Text(
                                "details produit / récap", style = TextStyle(
                                    fontSize = 13.sp,
                                    lineHeight = 17.sp
                                )
                            )
                        }
                        Column(
                            modifier = Modifier
                        ) {
                            Text(
                                "1 mois d’assurance", style = TextStyle(
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                            Spacer(modifier = Modifier.height(height = 16.dp))
                            Text(
                                "35.000 fcfa", style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight.W800,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(weight = 1f))
        Column {
            SelectField(
                values = paymentMethod,
                value = paymentMethod.first(),
                placeholder = "Question santé",
                label = "Payer par :",
                heightFraction = 0.6f,
                onChange = {},
                key = { it.key },
                modelValue = { v ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(size = 22.dp),
                            painter = painterResource(v.resource),
                            contentDescription = stringResource(v.name)
                        )
                        Text(
                            text = stringResource(v.name), style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.W400,
                                fontFamily = FontFamily.Default,
                            )
                        )
                    }
                },
                filter = { data, query ->
                    context.getString(data.name).lowercase().contains(query.lowercase())
                },
            ) { data, select ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                ) {
                    Row(
                        modifier = Modifier.weight(weight = 1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(size = 32.dp),
                            painter = painterResource(data.resource),
                            contentDescription = stringResource(data.name)
                        )
                        Text(
                            text = stringResource(data.name),
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.W600
                            )
                        )
                    }
                    if (data.key == select?.key)
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                }

            }
            Spacer(modifier = Modifier.height(height = 32.dp))
        }
    }
}


@Preview
@Composable
private fun PaidSubscribePreview() {
    MobilityHealthTheme {
        PaidSubscribe()
    }
}