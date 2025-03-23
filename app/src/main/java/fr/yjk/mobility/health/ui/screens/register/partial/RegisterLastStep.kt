package fr.yjk.mobility.health.ui.screens.register.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.components.DateField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Composable
fun RegisterLastStep(
    customer: CustomerRequest,
    onChange: (customerRequest: CustomerRequest) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {

        DateField(
            value = if (customer.birthday != null) Date.from(
                customer.birthday.atZone(ZoneId.systemDefault()).toInstant()
            ) else null,
            label = stringResource(R.string.birthday_label),
            placeholder = stringResource(R.string.birthday_hint),
            onChange = {
                onChange(
                    customer.copy(
                        birthday = LocalDateTime.ofInstant(
                            it.toInstant(),
                            ZoneId.systemDefault()
                        )
                    )
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.date),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            }
        )
        CustomTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            value = customer.phoneNumber ?: "",
            onValueChange = {
                onChange(customer.copy(phoneNumber = it))
            },
            label = stringResource(R.string.main_number_label),
            placeholder = stringResource(R.string.main_number_hint),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            value = customer.whatsappNumber ?: "",
            onValueChange = {
                onChange(customer.copy(whatsappNumber = it))
            },
            label = stringResource(R.string.num_ro_whatsapp),
            placeholder = stringResource(R.string.entrer_votre_num_ro_whatsapp),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.whatsapp),
                    contentDescription = null,
                    modifier = Modifier.size(size = 22.dp)
                )
            })

        CustomTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = customer.email ?: "",
            onValueChange = {
                onChange(customer.copy(email = it))
            },
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
        //RegisterLastStep()
    }
}
