package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
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
import fr.yjk.mobility.health.fake.Country
import fr.yjk.mobility.health.ui.components.SelectField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun SubscribePositionInfo() {
    val context = LocalContext.current
    val countries = Country.items
    val nationalityList = Country.NationalityItems

    Column(modifier = Modifier.verticalScroll(state = rememberScrollState()),verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .height(255.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.doc01),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(height = 8.dp))
        SelectField(
            values = nationalityList,
            value = nationalityList.last(),
            placeholder = stringResource(R.string.nationality_hint),
            label = stringResource(R.string.nationality_label),
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

        SelectField(
            values = countries,
            value = countries.last(),
            placeholder = stringResource(R.string.cc_start_hint),
            label = stringResource(R.string.cc_start_label),
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
        SelectField(
            values = countries,
            value = countries.first(),
            placeholder = stringResource(R.string.cc_end_hint),
            label = stringResource(R.string.cc_end_label),
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

    }
}

@Preview
@Composable
private fun SubscribePositionInfoPreview() {
    MobilityHealthTheme {
        SubscribePositionInfo()
    }
}