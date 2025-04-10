package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun CustomOutlineButton(
    text: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        contentPadding = PaddingValues(vertical = 16.dp),
        shape = RoundedCornerShape(14.dp),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        border =  BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
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
        CustomOutlineButton(
            text = "dfdf",
            onClick = {}
        )
    }
}