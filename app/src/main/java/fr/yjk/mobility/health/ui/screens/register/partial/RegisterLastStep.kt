package fr.yjk.mobility.health.ui.screens.register.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
fun RegisterLastStep() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.birthday_label),
            placeholder = stringResource(R.string.birthday_hint),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.date),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.main_number_label),
            placeholder = stringResource(R.string.main_number_hint),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.num_ro_whatsapp),
            placeholder = stringResource(R.string.entrer_votre_num_ro_whatsapp),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.whatsapp),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = stringResource(R.string.lrEmail),
            placeholder = stringResource(R.string.lrEmailHint),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.key),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })
    }
}


@Preview
@Composable
private fun RegisterLastStepPreview() {
    MobilityHealthTheme {
        RegisterLastStep()
    }
}
