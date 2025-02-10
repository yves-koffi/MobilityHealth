package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(verticalArrangement = Arrangement.spacedBy(space = 4.dp)) {
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 2.dp),
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Default,
            )

        )
        TextField(
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Default,
            ),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(size = 12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = TextFieldDefaults.colors().unfocusedContainerColor.copy(
                    alpha = 0.4f
                ),
                unfocusedLeadingIconColor = TextFieldDefaults.colors().unfocusedLeadingIconColor.copy(
                    alpha = 0.8f
                )
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {
    var textValue by remember { mutableStateOf("") }
    CustomTextField(
        value = textValue,
        onValueChange = { textValue = it },
        label = "Nom",
        placeholder = "Nom",
        modifier = Modifier // Ajoutez des modificateurs ici si nécessaire
    )
}