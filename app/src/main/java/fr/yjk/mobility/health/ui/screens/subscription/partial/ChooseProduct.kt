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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun ChooseProduct() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
        item {
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(stringResource(R.string.chooseTitle))
        }
        items(count = 4, key = { i -> i }) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                Column {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(224.dp),
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
                            Text(stringResource(R.string.assistance_voyage))
                            Text(
                                stringResource(R.string.price), style = TextStyle(
                                fontWeight = FontWeight.W500,
                                fontSize = 16.sp,
                                lineHeight = 18.sp
                            )
                            )
                        }
                        TextButton(onClick = {}) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.nav),
                                    contentDescription = null,
                                    Modifier.size(size = 16.dp)
                                )
                                Text(stringResource(R.string.details))
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ChooseProductPreview() {
    MobilityHealthTheme {
        ChooseProduct()
    }
}