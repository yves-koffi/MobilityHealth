package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme


@Composable
fun SubscribeContactInfo() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState()),verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {

        Spacer(modifier = Modifier.height(height = 32.dp))
        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.confirm_phone),
            placeholder = stringResource(R.string.phone_number),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.smartphone),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.number_whatsapp),
            placeholder = stringResource(R.string.phone_number),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.smartphone),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            })

    }
}


@Preview
@Composable
private fun SubscribeContactInfoPreview() {
    MobilityHealthTheme {
        SubscribeContactInfo()
    }
}