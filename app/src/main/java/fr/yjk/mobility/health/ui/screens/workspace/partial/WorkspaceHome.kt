package fr.yjk.mobility.health.ui.screens.workspace.partial

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.localProvider.LocalPreferences
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import java.time.format.DateTimeFormatter

@Composable
fun WorkspaceHome(onSubscribe: () -> Unit) {
     val authUIState = LocalPreferences.current.authUIState.collectAsState().value
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val win = (view.context as Activity).window
            WindowCompat.getInsetsController(win, view).isAppearanceLightStatusBars = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderHomeWorkspace(onSubscribe = onSubscribe)
        Box(
            modifier = Modifier
                .absoluteOffset(y = (-42).dp)
                .height(height = 232.dp)
                .fillMaxWidth(0.9f),
        ) {
            Image(
                painter = painterResource(R.drawable.card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
           authUIState.currentUser?.let{ auth->
                Text(
                    modifier = Modifier.offset(x = 32.dp, y = 134.dp),
                    text = auth.customer.card.issueDate.format(DateTimeFormatter.ofPattern("M/yy")).padStart(5,'0'),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                )
                Text(
                    modifier = Modifier.offset(x = 32.dp, y = 155.dp),
                    text = "${auth.customer.lastname} ${auth.customer.firstname}".uppercase(),
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                )
                Text(
                    modifier = Modifier.offset(x = 32.dp, y = 194.dp),
                    text = auth.customer.card.insuredNumber.chunked(4).joinToString(" ").uppercase(),
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                )

                Text(
                    modifier = Modifier.offset(x = 255.dp, y = 198.dp),
                    text = auth.customer.card.cardNumber.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.White),
                )
                Box(
                    modifier = Modifier
                        .offset(x = 252.dp, y = 90.dp)
                        .clip(RoundedCornerShape(size = 2.dp))
                        .size(88.dp,(10+88).dp)
                ) {
                    AsyncImage(
                        placeholder = painterResource(R.drawable.user),
                        model = auth.customer.avatar?:"",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }


        }
    }
}


@Preview
@Composable
private fun WorkspaceHomePreview() {
    MobilityHealthTheme {
        WorkspaceHome() {

        }
    }
}


