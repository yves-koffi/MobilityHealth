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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.screens.login.partial.CustomTab
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRequestScreen(onVerify: () -> Unit,onBack: () -> Unit) {

    var tabIndex by remember {
        mutableIntStateOf(0)
    }

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
                verticalArrangement = Arrangement.spacedBy(24.dp)
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
                CustomTab(index = tabIndex, onChange = { index ->
                    tabIndex = index
                })
                Spacer(modifier = Modifier.height(height = 24.dp))
                Text(
                    stringResource(R.string.login), style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    )
                )
                CustomTextField(
                    value = "",
                    onValueChange = { },
                    label = stringResource(if (tabIndex == 0) R.string.lrEmail else R.string.lrNumWhatsapp),
                    placeholder = stringResource(if (tabIndex == 0) R.string.lrEmailHint else R.string.lrNumWhatsappHint),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "user"
                        )
                    }
                )
                CustomButton(text = stringResource(R.string.lrValidateBtn)) {
                    onVerify()
                }
            }
        }
    }
}




@Preview
@Composable
private fun LoginRequestScreenPreview() {
    MobilityHealthTheme {
        LoginRequestScreen(onVerify = {}) {

        }
    }
}