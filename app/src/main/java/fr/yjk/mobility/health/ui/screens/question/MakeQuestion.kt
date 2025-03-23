// Ce fichier définit le composant MakeDirectory utilisé pour l'écran de création de répertoire.
package fr.yjk.mobility.health.ui.screens.question

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.localProvider.LocalPreferences
import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.QuestionRequest
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.ErrorLabel
import fr.yjk.mobility.health.ui.screens.directory.partial.MakeDirectoryFirstStep
import fr.yjk.mobility.health.ui.screens.directory.partial.MakeDirectoryLastStep
import fr.yjk.mobility.health.ui.screens.question.partial.MakeQuestionEnd
import fr.yjk.mobility.health.ui.screens.question.partial.MakeQuestionStart
import fr.yjk.mobility.health.ui.screens.register.RegisterViewModel
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import fr.yjk.mobility.health.utils.extensions.FailureType
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeQuestion(
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>(),
    onHome: () -> Unit
) {
    var error: String? by remember {
        mutableStateOf(null)
    }
    var isProgress: Boolean by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val localPreferences = LocalPreferences.current
    var fields: List<QuestionGroup> by remember { mutableStateOf(listOf()) }

    val snackbarHostState = remember { SnackbarHostState() }

    BackHandler {
        if (pagerState.currentPage == 1) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(0)
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },topBar = {

        TopAppBar(navigationIcon = {
            if (pagerState.currentPage > 0) {
                IconButton(onClick = {
                    if (pagerState.currentPage == 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }, title = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    modifier = Modifier
                        .width(width = 82.5.dp)
                        .height(28.43.dp),
                    painter = painterResource(R.drawable.lgoo2),
                    contentDescription = "logo"
                )
            }
        })
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ErrorLabel(
                        error = error
                    )
                }
                HorizontalPager(
                    userScrollEnabled = false,
                    modifier = Modifier.weight(weight = 1f),
                    verticalAlignment = Alignment.Top,
                    state = pagerState
                ) { page ->
                    if (page == 0) {
                        MakeQuestionStart()
                    } else {
                        MakeQuestionEnd(fields = fields){
                            fields = it
                        }
                    }

                }
                Spacer(modifier = Modifier.height(height = 4.dp))
                CustomButton(
                    progress = isProgress,
                    text = stringResource(if (pagerState.currentPage == 0) R.string.btnNext else R.string.lrValidateBtn),
                    icon = {
                        if (pagerState.currentPage == 0) {
                            Icon(
                                painter = painterResource(R.drawable.next),
                                contentDescription = null
                            )
                        }
                    }) {
                    if (pagerState.currentPage == 0) {
                        if(fields.isEmpty()){
                            registerViewModel.findQuestions { response ->
                                isProgress = response is UiResult.Loading
                                if (response is UiResult.Success) {
                                    fields = response.data
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(1)
                                    }
                                }
                                if (response is UiResult.Error) {
                                    val failure = response.failure
                                    if(failure.type == FailureType.Validation){
                                        error = failure.message
                                    }else{
                                        coroutineScope.launch {
                                            snackbarHostState
                                                .showSnackbar(
                                                    message = failure.message,
                                                    actionLabel = "Ok".uppercase(),
                                                    duration = SnackbarDuration.Short
                                                )
                                        }
                                    }
                                }
                            }
                        }else{
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    } else {
                        if (fields.all { it.validate() }){
                            registerViewModel.saveAnswers(QuestionRequest(questions = fields)) { response ->
                                isProgress = response is UiResult.Loading
                                if (response is UiResult.Success) {
                                    onHome()
                                }
                            }
                        }else{
                            error = "Veuillez compléter tous les champs du formulaire"
                        }

                        //onSubscribe()
                    }

                }
                Spacer(modifier = Modifier.height(height = 8.dp))
            }
        }
    }
}


@Preview
@Composable
private fun MakeDirectoryPreview() {
    MobilityHealthTheme {
        MakeQuestion() {

        }
    }
}
