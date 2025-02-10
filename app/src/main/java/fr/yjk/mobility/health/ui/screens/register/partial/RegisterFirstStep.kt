package fr.yjk.mobility.health.ui.screens.register.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.fake.Country
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.components.SelectField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun RegisterFirstStep() {
    val context= LocalContext.current
    val countries = Country.items
    val nationalityList = Country.NationalityItems
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {

        SelectField(
            values = countries,
            value = countries.first(),
            placeholder = "Pays de Résidence",
            label = "Sélectionner Votre pays de Résidence habituel",
            heightFraction = 0.7f,
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
            filter = { data, query -> context.getString(data.name).lowercase().contains(query.lowercase())  },
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
            values = nationalityList,
            value = nationalityList.last(),
            placeholder = "Entrez votre Nationalité",
            label = "Nationalité",
            heightFraction = 0.6f,
            onChange = {},
            key = {  it.key },
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
            filter = { data, query -> context.getString(data.name).lowercase().contains(query.lowercase())  },
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

        CustomTextField(value = "Entrer Votre Nom",
            onValueChange = { },
            label = "Nom",
            placeholder = "Entrer Votre Nom",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Prénoms",
            placeholder = "Entrer Votre Prénom",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })
    }
}

@Preview
@Composable
private fun RegisterFirstStepPreview() {
    MobilityHealthTheme {
        RegisterFirstStep()
    }
}