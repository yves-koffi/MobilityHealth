package fr.yjk.mobility.health.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.ErrorLabel
import fr.yjk.mobility.health.ui.screens.register.partial.RegisterFirstStep
import fr.yjk.mobility.health.ui.screens.register.partial.RegisterLastStep
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import fr.yjk.mobility.health.utils.extensions.Failure
import fr.yjk.mobility.health.utils.extensions.FailureType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStep(
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>(),
    onVerify: (String) -> Unit, onBack: () -> Unit
) {
    var error: String? by remember {
        mutableStateOf(null)
    }
    var isProgress: Boolean by remember {
        mutableStateOf(false)
    }
    val localPreferences = LocalPreferences.current
    val pagerState = rememberPagerState(pageCount = {
        2
    })

    var customerRequest: CustomerRequest by remember {
        mutableStateOf(CustomerRequest())
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                if (pagerState.currentPage == 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                } else {
                    onBack()
                }
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
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
                    .padding(all = scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(space = 6.dp)) {
                    for (i in 0..1) Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(size = 4.dp))
                            .height(4.dp)
                            .width(width = 88.dp)
                            .background(
                                color = if (i == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(
                                    0.5f
                                )
                            )
                    )
                }
                Spacer(modifier = Modifier.height(height = 24.dp))
                Text(
                    text = stringResource(R.string.register_title), style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
                )
                Text(
                    stringResource(R.string.register_info),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400
                    ),
                    textDecoration = TextDecoration.Underline
                )
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
                        RegisterFirstStep(
                            customer = customerRequest,
                            onChange = {
                                customerRequest = it
                            },
                        )
                    } else {
                        RegisterLastStep(
                            customer = customerRequest,
                            onChange = {
                                customerRequest = it
                            },
                        )
                    }

                }
                Text(
                    stringResource(R.string.confirm_condition_use),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                    ),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                CustomButton(
                    progress = isProgress,
                    text = stringResource(if (pagerState.currentPage == 0) R.string.btnNext else R.string.sign_up),
                    icon = {
                        Icon(painter = painterResource(R.drawable.next), contentDescription = null)
                    },
                ) {
                    if (pagerState.currentPage == 1) {
                        if (customerRequest.lastValidated()) {
                            registerViewModel.register(data = customerRequest) { response ->
                                isProgress = response is UiResult.Loading
                                if (response is UiResult.Success) {
                                    onVerify(response.data.email)
                                    localPreferences.makeDirectory()
                                }
                                if (response is UiResult.Error) {
                                    val failure = response.failure
                                    if(failure.type == FailureType.Validation){
                                        error = failure.message
                                    }else{
                                        scope.launch {
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
                        } else {
                            error = "Veuillez compléter tous les champs du formulaire"
                        }
                    } else {
                        if (customerRequest.firstValidated()) {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        } else {
                            error = "Veuillez compléter tous les champs du formulaire"

                        }
                    }

                }
                Spacer(modifier = Modifier.height(height = 8.dp))
            }
        }
    }
}


@Preview
@Composable
private fun RegisterFirstStepPreview() {
    MobilityHealthTheme {
        RegisterStep(onVerify = {}, registerViewModel = hiltViewModel<RegisterViewModel>()) {

        }
    }
}
