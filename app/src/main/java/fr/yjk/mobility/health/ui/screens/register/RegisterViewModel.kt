package fr.yjk.mobility.health.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.yjk.mobility.health.utils.Feedback
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.yjk.mobility.health.data.register.RegisterRepository
import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.network.request.QuestionRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import fr.yjk.mobility.health.utils.extensions.toFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    fun register(
        data: CustomerRequest, on: Feedback<UiResult<OtpResponse>> = null
    ) {
        on?.invoke(UiResult.Loading)
        viewModelScope.launch {
            registerRepository.register(request = data).flowOn(Dispatchers.IO).catch { e ->
                on?.invoke(UiResult.Error(e.toFailure()))
            }.collect {
                on?.invoke(UiResult.Success(it))
            }
        }
    }

    fun findQuestions(
        on: Feedback<UiResult<List<QuestionGroup>>> = null
    ) {
        on?.invoke(UiResult.Loading)
        viewModelScope.launch {
            registerRepository.findQuestions().flowOn(Dispatchers.IO).catch { e ->
                on?.invoke(UiResult.Error(e.toFailure()))
            }.collect {
                on?.invoke(UiResult.Success(it))
            }
        }
    }

    fun saveAnswers(
        data: QuestionRequest,
        on: Feedback<UiResult<OtpResponse>> = null
    ) {
        on?.invoke(UiResult.Loading)
        viewModelScope.launch {
            registerRepository.saveAnswers(request = data).flowOn(Dispatchers.IO).catch { e ->
                on?.invoke(UiResult.Error(e.toFailure()))
            }.collect {
                on?.invoke(UiResult.Success(it))
            }
        }
    }
}