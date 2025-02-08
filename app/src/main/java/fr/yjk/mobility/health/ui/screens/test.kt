import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun OTPForm(otpLength: Int = 6, onOtpSubmit: (String) -> Unit) {
    val otp = remember { mutableStateListOf<String>().apply { repeat(otpLength) { add("") } } }
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Enter OTP", style = MaterialTheme.typography.headlineMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until otpLength) {
                OutlinedTextField(
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
                            }
                        } else {
                            // optionally provide feedback to user if invalid input
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
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

                LaunchedEffect(Unit) {
                    if (i == 0) {
                        focusRequesters[0].requestFocus() // Focus on first input field on launch
                    }
                }
            }
        }

        Button(onClick = {
            focusManager.clearFocus()
            onOtpSubmit(otp.joinToString(""))
        }) {
            Text("Verify OTP")
        }
    }
}

@Composable
fun OTPExample() {
    var otpValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OTPForm(
            otpLength = 6,
            onOtpSubmit = { otp ->
                otpValue = otp
                println("OTP Submitted: $otp")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Submitted OTP: $otpValue")
    }
}

// Preview provider (for Android Studio)
@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun OTPPreview() {
    MaterialTheme {
        OTPExample()
    }
}