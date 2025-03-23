package fr.yjk.mobility.health.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.yjk.mobility.health.model.auth.CurrentUser
import fr.yjk.mobility.health.network.config.SessionKeys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val sessionKeys: SessionKeys,
    private val preferencesRepository: PreferenceRepository
) :
    ViewModel() {

    private val _connectionStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _makeDirectoryStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isConnect = _connectionStateFlow.asStateFlow()
    val isMakeDirectory = _makeDirectoryStateFlow.asStateFlow()

    val authUIState: StateFlow<AuthUiState> =
        preferencesRepository.auth.map { auth ->
            AuthUiState(auth)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AuthUiState(isAuth = AuthState.Loading)
        )

    fun attempt(currentUser: CurrentUser) {
        viewModelScope.launch {
            sessionKeys.save(token = currentUser.token)
            preferencesRepository.saveAuthPreference(Json.encodeToString<CurrentUser>(currentUser))
        }
    }

    fun makeDirectory(value: Boolean = true) {
        viewModelScope.launch {
            _makeDirectoryStateFlow.value = value
        }
    }

    fun logout() {
        viewModelScope.launch {
            preferencesRepository.saveAuthPreference("")
        }
    }

    fun setConnectionState(state: Boolean) {
        _connectionStateFlow.value = state
    }
}

