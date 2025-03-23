package fr.yjk.mobility.health.network

import fr.yjk.mobility.health.utils.extensions.Failure


sealed interface UiResult<out R> {
    data class Success<out T>(val data: T) : UiResult<T>
    data class Error(val failure: Failure) : UiResult<Nothing>
    data object Loading : UiResult<Nothing>
    data object None : UiResult<Nothing>
}

