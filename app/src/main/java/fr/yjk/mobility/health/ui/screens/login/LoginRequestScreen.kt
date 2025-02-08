package fr.yjk.mobility.health.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol
import fr.yjk.mobility.health.ui.theme.scaffoldPadding

@Composable
fun LoginRequestScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = scaffoldPadding),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        modifier = Modifier
                            .width(width = 150.dp)
                            .height(51.dp),
                        painter = painterResource(R.drawable.lgoo2),
                        contentDescription = "logo"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .height(56.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        .border(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            width = 1.dp
                        )
                        .padding(all = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .weight(1f)
                            .fillMaxHeight()
                            .background(ButtonDefaults.buttonColors().containerColor)
                    ) {
                        Text(
                            "Email", style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 15.sp,
                                fontFamily = handelGotDBol,
                                fontWeight = FontWeight.W400,
                                color = ButtonDefaults.buttonColors().contentColor
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            "Numero whatsapp", style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 15.sp,
                                fontFamily = handelGotDBol,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(height = 24.dp))
                Text(
                    "Se connecter", style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = handelGotDBol,
                        letterSpacing = 0.15.sp
                    )
                )
                Column {
                    Text("Email")
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        placeholder = {
                            Text("Entrer Votre email")
                        },
                        onValueChange = {},
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "user"
                            )
                        },
                        shape = RoundedCornerShape(size = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                Button(
                    contentPadding = PaddingValues(vertical = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}) {
                    Text(
                        "Valider", style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = handelGotDBol,
                            letterSpacing = 0.sp
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginRequestScreenPreview() {
    MobilityHealthTheme {
        LoginRequestScreen()
    }
}