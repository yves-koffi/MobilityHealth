// Ce fichier définit le composant MakeDirectory utilisé pour l'écran de création de répertoire.
package fr.yjk.mobility.health.ui.screens.directory

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
import fr.yjk.mobility.health.ui.screens.directory.partial.MakeDirectoryFirstStep
import fr.yjk.mobility.health.ui.screens.directory.partial.MakeDirectoryLastStep
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeDirectory(onSubscribe:()->Unit) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })


    BackHandler {
        if (pagerState.currentPage == 1) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(0)
            }
        }
    }

    Scaffold(topBar = {

        TopAppBar(navigationIcon = {
            if(pagerState.currentPage>0){
                IconButton(onClick = {
                    if (pagerState.currentPage == 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = stringResource(R.string.pass),
                        modifier = Modifier.clickable {},
                        style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(height = 16.dp))
                Text(
                    text = stringResource(R.string.checkDir), style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
                )
                Text(
                    stringResource(R.string.dirInfo),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400
                    ),
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(height = 24.dp))
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
                HorizontalPager(
                    userScrollEnabled = false,
                    modifier = Modifier.weight(weight = 1f),
                    verticalAlignment = Alignment.Top,
                    state = pagerState
                ) { page ->
                    if (page == 0) {
                        MakeDirectoryFirstStep()
                    } else {
                        MakeDirectoryLastStep()
                    }

                }
                CustomButton(text = stringResource(if (pagerState.currentPage == 0) R.string.btnNext else R.string.lrValidateBtn),
                    icon = {
                        if (pagerState.currentPage == 0) {
                            Icon(
                                painter = painterResource(R.drawable.next),
                                contentDescription = null
                            )
                        }
                    }) {
                    coroutineScope.launch {
                        if(pagerState.currentPage == 0){
                            pagerState.animateScrollToPage(1)
                        }else{
                            onSubscribe()
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
private fun MakeDirectoryPreview() {
    MobilityHealthTheme {
        MakeDirectory(){

        }
    }
}
