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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.screens.register.RegisterStep
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun RegisterLastStep() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        CustomTextField(value = "",
            onValueChange = { },
            label = "Date de Naissance",
            placeholder = "Entrer Votre date de naissance",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.date),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Numéro de téléphone principal",
            placeholder = "Entrer Votre Numéro de téléphone",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Numéro Whatsapp",
            placeholder = "Entrer Votre Numéro Whatsapp",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.whatsapp),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Email",
            placeholder = "Placeholder",
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
