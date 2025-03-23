package fr.yjk.mobility.health.ui.screens.login.partial

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.EmailRequest
import fr.yjk.mobility.health.ui.components.ErrorLabel
import fr.yjk.mobility.health.ui.screens.login.LoginViewModel
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.utils.extensions.Failure
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ResendOTPCode(loginViewModel: LoginViewModel? = null, email: String) {
    val maxDuration = 59
    var waitDuration by remember {
        mutableIntStateOf(maxDuration)
    }
    var error: String? by remember {
        mutableStateOf(null)
    }
    var isProgress: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(waitDuration) {
        launch {
            while (waitDuration > 0) {
                delay(1000L)
                waitDuration -= 1
            }
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(
                R.string.optWait,
                if (waitDuration == 0) "" else "(0:${waitDuration.toString().padStart(2, '0')})"
            ), style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.75.sp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = if (waitDuration == 0) 0.9f else 0.7f)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                .padding(all = 14.dp)
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        if (isProgress) {
            CircularProgressIndicator(
                modifier = Modifier.size(size = 22.dp)
            )
        } else {
            Text(
                text = stringResource(R.string.optResend), style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = if (waitDuration == 0) Color.Unspecified else MaterialTheme.colorScheme.outline.copy(
                        alpha = 0.5f
                    )
                ),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    if (waitDuration == 0) {
                        loginViewModel?.generateOtp(data = EmailRequest(email = email)) { response ->
                            isProgress = response is UiResult.Loading
                            if (response is UiResult.Success) {
                                waitDuration = maxDuration

                            }
                            if (response is UiResult.Error) {
                                val failure = response.failure
                                error = failure.message
                            }
                        }
                    }


                }
            )
            ErrorLabel(
                error = error
            )
        }

    }
}


@Preview
@Composable
private fun ResendOTPCodePreview() {
    MobilityHealthTheme {
        ResendOTPCode(email = "yves.koffi@devolution.africa")
    }
}

