// Ce fichier définit le composant CustomTextField utilisé pour les champs de texte personnalisés dans l'application.
package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
    error: String? = null,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
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
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = error != null,
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = modifier,
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(size = 12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = TextFieldDefaults.colors().unfocusedContainerColor.copy(
                    alpha = 0.4f
                ),
                unfocusedLeadingIconColor = TextFieldDefaults.colors().unfocusedLeadingIconColor.copy(
                    alpha = 0.8f
                ),
                errorIndicatorColor = Color.Transparent
            ),
        )
        if (error != null) {
            ErrorLabel(error = error)
        }
    }
}

@Composable
fun ErrorLabel(error: String? = null) {
    Text(
        text = error ?: "",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 16.dp)
    )
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