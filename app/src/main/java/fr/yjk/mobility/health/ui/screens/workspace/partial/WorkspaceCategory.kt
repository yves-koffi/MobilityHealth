package fr.yjk.mobility.health.ui.screens.workspace.partial

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun WorkspaceCategory() {
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()
    if (!view.isInEditMode) {
        SideEffect {
            val win = (view.context as Activity).window
            WindowCompat.getInsetsController(win, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.8f),
            painter = painterResource(R.drawable.building),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.building),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun WorkspaceCategoryPreview() {
    MobilityHealthTheme {
        WorkspaceCategory()
    }
}