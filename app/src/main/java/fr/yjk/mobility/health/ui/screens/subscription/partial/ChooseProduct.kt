package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun ChooseProduct() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
        item {
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text("Votre produit d’assurance")
        }
        items(count = 4, key = { i -> i }) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp,
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
                            Text("Assistance voyage")
                            Text("Prix en fcfa")
                        }
                        TextButton(onClick = {}) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = null
                                )
                                Text("Détails")
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