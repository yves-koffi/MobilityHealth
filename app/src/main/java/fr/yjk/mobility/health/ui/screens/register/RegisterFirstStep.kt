package fr.yjk.mobility.health.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStep(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                if(pagerState.currentPage == 1){
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
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
                Spacer(modifier = Modifier.height(height = 16.dp))
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
                Spacer(modifier = Modifier.height(height = 32.dp))
                Text(
                    text = "Inscription", style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    ), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Ne vous inquiétez pas, vous seul pouvez voir vos informations personnelles, personne d'autre ne pourra les voir.",
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
                    if(page == 0){
                        RegisterFirstStep()
                    }else{
                        RegisterLastStep()
                    }

                }
                Text(
                    "En appuyant sur “suivant”, vous acceptez nos conditions générales et notre politique de confidentialité.",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                    ),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

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
fun RegisterFirstStep() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        CustomTextField(value = "",
            onValueChange = { },
            label = "Sélectionner Votre pays de Résidence habituel",
            placeholder = "Pays de Résidence",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Nationalité",
            placeholder = "Entrez votre Nationalité",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Nom",
            placeholder = "Entrer Votre Nom",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
        CustomTextField(value = "",
            onValueChange = { },
            label = "Prénoms",
            placeholder = "Entrer Votre Prénom",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })
    }
}

@Composable
fun RegisterLastStep() {
    Column(verticalArrangement = Arrangement.spacedBy(space = 14.dp)) {
        CustomTextField(value = "",
            onValueChange = { },
            label = "Date de Naissance",
            placeholder = "Entrer Votre date de naissance",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Numéro de téléphone principal",
            placeholder = "Entrer Votre Numéro de téléphone",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Numéro Whatsapp",
            placeholder = "Entrer Votre Numéro Whatsapp",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            })

        CustomTextField(value = "",
            onValueChange = { },
            label = "Email",
            placeholder = "Placeholder",
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
private fun RegisterFirstStepPreview() {
    MobilityHealthTheme {
        RegisterStep()
    }
}
