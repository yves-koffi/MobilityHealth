import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@Composable
fun WorkspaceTravel() {
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
        Text(text = "Workspace travel", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview
@Composable
private fun WorkspaceTravelPreview() {
    MobilityHealthTheme {
        WorkspaceTravel()
    }
}