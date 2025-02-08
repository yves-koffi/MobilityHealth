package fr.yjk.mobility.health.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding

@Composable
fun LoginOtpScreen(modifier: Modifier = Modifier) {
    var otpValue by remember { mutableStateOf("") }
    Scaffold { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = scaffoldPadding),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        modifier = Modifier
                            .width(width = 150.dp)
                            .height(51.dp),
                        painter = painterResource(R.drawable.lgoo2),
                        contentDescription = "logo"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(
                        "Entrer le code otp", style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            letterSpacing = 0.15.sp
                        )
                    )
                    Text(
                        "Veuillez confirmer le code OTP recu par sms", style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                        ), textDecoration = TextDecoration.Underline
                    )
                }
                OTPForm(
                    otpLength = 6,
                    onOtpSubmit = { otp ->
                        otpValue = otp
                        println("OTP Submitted: $otp")
                    }
                )
                Text(
                    "Pas encore reçu de code? (0:09)", style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.75.sp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 8.dp))
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                        .padding(all = 14.dp)
                )
                Text(
                    "Renvoyez le code", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = handelGotDBol
                    ), textDecoration = TextDecoration.Underline
                )
                Button(
                    contentPadding = PaddingValues(vertical = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}) {
                    Text(
                        "Valider", style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            letterSpacing = 0.sp
                        )
                    )
                }
            }
        }
    }
}


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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until otpLength) {
                TextField(
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
private fun LoginOtpScreenPreview() {
    MobilityHealthTheme {
        LoginOtpScreen()
    }
}