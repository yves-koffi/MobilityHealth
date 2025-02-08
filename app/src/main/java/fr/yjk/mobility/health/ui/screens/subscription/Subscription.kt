package fr.yjk.mobility.health.ui.screens.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Subscription(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        5
    }, initialPage = 4)
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                if (pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        }, title = {
            /* Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                 Image(
                     modifier = Modifier
                         .width(width = 82.5.dp)
                         .height(28.43.dp),
                     painter = painterResource(R.drawable.lgoo2),
                     contentDescription = "logo"
                 )
             }*/
        })
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

               /* Dialog(properties = DialogProperties(
                    usePlatformDefaultWidth = false
                ),onDismissRequest = { }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.7f)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(height = 24.dp))
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(200.dp),
                                contentScale = ContentScale.Crop,
                                painter = painterResource(R.drawable.success),
                                contentDescription = null
                            )
                            Text(text = "Merci,",style = TextStyle(
                                fontSize = 32.sp,
                                lineHeight = 39.sp,
                                fontWeight = FontWeight.W400,
                                fontFamily = handelGotDBol,
                                letterSpacing = 0.15.sp
                            ))
                            Text(
                                text = "Nous avons bien reçu votre paiement",
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W300
                                )
                            )
                        }
                    }
                }*/

                CustomButton(text = "Suivant", icon = Icons.AutoMirrored.Outlined.ArrowForward) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
                Spacer(modifier = Modifier.height(height = 8.dp))
            }
        }
    }
}

@Composable
fun ChooseProduct() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
        item {
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text("Votre produit d’assurance")
        }
        items(count = 4, key = { i -> i }) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                Column {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(255.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.product01),
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Assistance voyage")
                            Text("Prix en fcfa")
                        }
                        TextButton(onClick = {}) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = null
                                )
                                Text("Détails")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubscribePositionInfo() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .height(255.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.doc01),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(height = 8.dp))
        CustomTextField(value = "",
            onValueChange = { },
            label = "Sélectionner Votre pays de Résidence habituel",
            placeholder = "Azeirbandjan",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Sélectionner Votre pays de départ",
            placeholder = "Cote d’ivoire",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Sélectionner Votre pays de destination",
            placeholder = "Azeirbandjan",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })

    }
}

@Composable
fun SubscribeUserInfo() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(255.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.product01),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Assistance voyage")
                        Text("Prix en fcfa")
                    }
                    TextButton(onClick = {}) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null
                            )
                            Text("Détails")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Questions de santé", style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                fontFamily = handelGotDBol,
                letterSpacing = 0.15.sp
            )
        )
        for (i in 1..4)
            CustomTextField(value = "",
                onValueChange = { },
                label = "Question $i",
                placeholder = "Question santé",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                })
    }
}

@Composable
fun SubscribeContactInfo() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        CustomTextField(value = "",
            onValueChange = { },
            label = "Confirmer votre numéro de téléphone",
            placeholder = "+225 O7 O7 O7 O7",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Confirmer votre Numéro whatsapp",
            placeholder = "+225 O7 O7 O7 O7",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })

    }
}

@Composable
fun PaidSubscribe() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(164.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.product01),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Row {
                        Column(
                            modifier = Modifier
                        ) {
                            Text("produit d’assistance")
                            Text("details produit / récap")
                        }
                        Column(
                            modifier = Modifier
                        ) {
                            Text("1 mois d’assurance")
                            Text("35.000 fcfa")
                        }
                    }
                }
            }
        }
        CustomTextField(value = "",
            onValueChange = { },
            label = "Payer par :",
            placeholder = "Question santé",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
    }
}

@Preview
@Composable
private fun SubscriptionPreview() {
    MobilityHealthTheme {
        Subscription()
    }
}