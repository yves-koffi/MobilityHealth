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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.screens.register.partial.RegisterFirstStep
import fr.yjk.mobility.health.ui.screens.register.partial.RegisterLastStep
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStep(onVerify: () -> Unit,onBack: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                if (pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }else{
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
                Spacer(modifier = Modifier.height(height = 8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    for (i in 0..1) Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(size = 8.dp))
                            .height(8.dp)
                            .width(width = 100.dp)
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
                Spacer(modifier = Modifier.height(height = 24.dp))
                HorizontalPager(
                    userScrollEnabled = false,
                    modifier = Modifier.weight(weight = 1f),
                    verticalAlignment = Alignment.Top,
                    state = pagerState
                ) { page ->
                    if (page == 0) {
                        RegisterFirstStep()
                    } else {
                        RegisterLastStep()
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

                CustomButton(text = stringResource(R.string.btnNext), icon = {
                    Icon(painter = painterResource(R.drawable.next), contentDescription = null)
                }) {
                    if (pagerState.currentPage == 1) {
                        onVerify()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
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
        RegisterStep(onVerify = {}) {

        }
    }
}
