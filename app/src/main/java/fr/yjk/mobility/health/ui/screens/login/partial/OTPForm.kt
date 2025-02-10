package fr.yjk.mobility.health.ui.screens.login.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun OTPForm(otpLength: Int = 6, onOtpSubmit: (String) -> Unit) {
    val otp = remember { mutableStateListOf<String>().apply { repeat(otpLength) { add("") } } }
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until otpLength) {
                TextField(

                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = otp[i],
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            otp[i] = newValue
                            if (newValue.isNotEmpty()) {
                                if (i < otpLength - 1) {
                                    focusRequesters[i + 1].requestFocus()
                                } else {
                                    focusManager.clearFocus() // Hide keyboard on last digit
                                    onOtpSubmit(otp.joinToString("")) // Submit OTP
                                }
                            } else {
                                otp[i] = ""
                                if (i >= 1) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            }
                        } else {
                            //
                        }
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusRequesters[i]),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (i == otpLength - 1) ImeAction.Done else ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus() // Hide keyboard on last digit
                            onOtpSubmit(otp.joinToString("")) // Submit OTP
                        },
                        onNext = {
                            if (i < otpLength - 1) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        }
                    ),
                    singleLine = true,
                    maxLines = 1
                )

                /*LaunchedEffect(Unit) {
                    if (i == 0) {
                        focusRequesters[0].requestFocus() // Focus on first input field on launch
                    }
                }*/
            }
        }
        /* Button(onClick = {
             focusManager.clearFocus()
             onOtpSubmit(otp.joinToString(""))
         }) {
             Text("Verify OTP")
         }*/
    }
}

@Preview
@Composable
private fun OTPFormPreview() {
    MobilityHealthTheme {
        OTPForm(otpLength=6) {

        }
    }
}