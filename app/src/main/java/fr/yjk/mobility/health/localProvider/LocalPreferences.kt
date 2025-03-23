package fr.yjk.mobility.health.localProvider

import androidx.compose.runtime.compositionLocalOf
import fr.yjk.mobility.health.preferences.PreferenceViewModel


val LocalPreferences = compositionLocalOf<PreferenceViewModel>{
    error("Error")
}