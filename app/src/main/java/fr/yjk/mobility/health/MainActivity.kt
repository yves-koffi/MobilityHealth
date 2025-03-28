package fr.yjk.mobility.health

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import fr.yjk.mobility.health.localProvider.LocalPreferences
import fr.yjk.mobility.health.preferences.PreferenceViewModel
import fr.yjk.mobility.health.ui.navigation.MainAppNavHost
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userPreferenceViewModel: PreferenceViewModel by viewModels<PreferenceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreenView -> splashScreenView.remove() }
        }
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MobilityHealthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        LocalPreferences provides userPreferenceViewModel
                    ) {
                        MainAppNavHost()
                    }
                }
            }
        }
    }
}
