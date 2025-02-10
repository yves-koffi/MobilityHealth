package fr.yjk.mobility.health.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.screens.login.partial.OTPForm
import fr.yjk.mobility.health.ui.screens.login.partial.ResendOTPCode
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginOtpScreen(onHome: () -> Unit,onBack: () -> Unit) {
    var otpValue by remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                onBack()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        }, title = {})
    }) { innerPadding ->
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
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter,
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        stringResource(id = R.string.optTitle), style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            letterSpacing = 0.15.sp
                        )
                    )
                    Text(
                        stringResource(R.string.optInfo), style = TextStyle(
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
                    }
                )
                ResendOTPCode()
                Spacer(modifier = Modifier.height(8.dp))
                CustomButton(
                    enabled = otpValue.length == 6,
                    text = stringResource(R.string.lrValidateBtn)
                ) {
                    onHome()
                }
            }
        }
    }
}



@Preview
@Composable
private fun LoginOtpScreenPreview() {
    MobilityHealthTheme {
        LoginOtpScreen(onHome = {}) {

        }
    }
}