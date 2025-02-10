package fr.yjk.mobility.health.ui.screens.subscription.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol

@Composable
fun PaymentSuccessMessage() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height = 32.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.success),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(height = 32.dp))
            Text(
                text = stringResource(R.string.thanks), style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = handelGotDBol,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = stringResource(R.string.paid_success),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300
                )
            )
        }
    }
}


@Preview
@Composable
private fun PaymentSuccessMessagePreview() {
    MobilityHealthTheme {
        PaymentSuccessMessage()
    }
}