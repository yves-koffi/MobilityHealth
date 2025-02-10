package fr.yjk.mobility.health.ui.screens.workspace.partial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol

@Composable
fun HeaderHomeWorkspace(onSubscribe: () -> Unit) {
    val colors = listOf(
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary
    )
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val density = LocalDensity.current.density

    val degrade = Brush.linearGradient(
        colors = colors,
        start = Offset(screenWidth.value * density, 0f),
        end = Offset(0f, screenHeight.value * density)
    )

    Box(
        modifier = Modifier
            .height(255.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .fillMaxSize()
                .background(
                    brush = degrade,
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                Column(modifier = Modifier.weight(weight = 1f)) {
                    Text(
                        stringResource(R.string.welcome, "Marc"), style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(height = 24.dp))
                    Text(
                        stringResource(R.string.homeInfo), style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 31.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            color = Color.White
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(size = 11.dp))
                            .size(size = 88.dp)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(R.drawable.user),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(height = 14.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        onClick = {
                            onSubscribe()
                        },
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.subscribeBtn), style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HeaderWorkspacePreview() {
    MobilityHealthTheme {
        HeaderHomeWorkspace(){

        }
    }
}