package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.ui.theme.handelGotDBol


@Composable
fun CustomButton(
    text: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        contentPadding = PaddingValues(vertical = 16.dp),
        shape = RoundedCornerShape(14.dp),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = text, style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = handelGotDBol,
                    letterSpacing = 0.sp
                ), textAlign = TextAlign.Center,
                modifier = Modifier.weight(weight = 1f)
            )
            if (icon != null) Icon(imageVector = icon, contentDescription = null)
        }
    }
}


@Preview
@Composable
private fun CustomButtonPreview() {
    MobilityHealthTheme {
        CustomButton(
            text = "dfdf",
            onClick = {}
        )
    }
}