package fr.yjk.mobility.health.ui.screens.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.screens.subscription.partial.ChooseProduct
import fr.yjk.mobility.health.ui.screens.subscription.partial.PaidSubscribe
import fr.yjk.mobility.health.ui.screens.subscription.partial.PaymentSuccessMessage
import fr.yjk.mobility.health.ui.screens.subscription.partial.SubscribeContactInfo
import fr.yjk.mobility.health.ui.screens.subscription.partial.SubscribePositionInfo
import fr.yjk.mobility.health.ui.screens.subscription.partial.SubscribeUserInfo
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Subscription(onHome:()->Unit) {
    var paid by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        5
    }, initialPage = 0)
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            if(pagerState.currentPage>0){
                IconButton(onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                }
            }

        }, title = {})
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                Text(
                    text = "Choisir son produit d’assistance", style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(height = 6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    for (i in 0..4) Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(size = 8.dp))
                            .height(8.dp)
                            .width(width = 56.dp)
                            .background(
                                color = if (i == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(
                                    0.5f
                                )
                            )
                    )
                }
                Spacer(modifier = Modifier.height(height = 14.dp))
                HorizontalPager(
                    userScrollEnabled = false,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    state = pagerState
                ) { page ->
                    when (page) {
                        0 -> {
                            ChooseProduct()
                        }

                        1 -> {
                            SubscribePositionInfo()
                        }

                        2 -> {
                            SubscribeUserInfo()
                        }

                        3 -> {
                            SubscribeContactInfo()
                        }

                        4 -> {
                            PaidSubscribe()
                        }
                    }

                }

                if (paid) {
                    Dialog(properties = DialogProperties(
                        usePlatformDefaultWidth = false
                    ), onDismissRequest = {
                        paid = false
                        onHome()
                    }) {
                        PaymentSuccessMessage()
                    }
                }

                CustomButton(
                    text = if (pagerState.currentPage == 0) "Suivant" else "Proceder au paiement",
                    icon = {
                        Icon(painter = painterResource(R.drawable.next), contentDescription = null)
                    }) {
                    if (pagerState.currentPage == 4){
                        paid = true
                    }
                    coroutineScope.launch {
                        if (pagerState.currentPage < 5) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
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
private fun SubscriptionPreview() {
    MobilityHealthTheme {
        Subscription(){

        }
    }
}