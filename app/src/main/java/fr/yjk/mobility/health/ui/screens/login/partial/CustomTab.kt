package fr.yjk.mobility.health.ui.screens.login.partial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol

@Composable
fun CustomTab(index: Int, onChange: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .height(54.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
            .border(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                width = 1.dp
            )
            .padding(all = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable {
                    onChange(0)
                }
                .clip(RoundedCornerShape(8.dp))
                .weight(1f)
                .fillMaxHeight()
                .background(if (index == 0) ButtonDefaults.buttonColors().containerColor else Color.Unspecified)
        ) {
            Text(
                stringResource(R.string.lrEmail), style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    fontFamily = handelGotDBol,
                    fontWeight = FontWeight.W400,
                    color = if (index == 0) ButtonDefaults.buttonColors().contentColor else Color.Unspecified
                )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable {
                    onChange(1)
                }
                .clip(RoundedCornerShape(8.dp))
                .weight(1f)
                .fillMaxHeight()
                .background(if (index == 1) ButtonDefaults.buttonColors().containerColor else Color.Unspecified)
        ) {
            Text(
                stringResource(R.string.lrNumWhatsapp), style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    fontFamily = handelGotDBol,
                    fontWeight = FontWeight.W400,
                    color = if (index == 1) ButtonDefaults.buttonColors().contentColor else Color.Unspecified
                )
            )
        }
    }
}



@Preview
@Composable
private fun CustomTabPreview() {
    MobilityHealthTheme {
        CustomTab(index=0) {

        }
    }
}