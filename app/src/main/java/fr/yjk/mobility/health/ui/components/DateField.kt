package fr.yjk.mobility.health.ui.components

import android.icu.text.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    modifier: Modifier = Modifier,
    value: Date? = null,
    error: String? = null,
    placeholder: String,
    label: String,
    enabled: Boolean = true,
    dateStyle: Int = DateFormat.DEFAULT,
    onChange: ((value: Date) -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    val df = DateFormat.getDateInstance(dateStyle, Locale.getDefault())

    val focusRequester = remember { FocusRequester() }
    val isOpen = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    val isFocused = interactionSource.collectIsFocusedAsState().value
    val bottomColor =
        if (isFocused) TextFieldDefaults.colors().focusedIndicatorColor else TextFieldDefaults.colors().unfocusedIndicatorColor

    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = value?.time)

    Column {
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 6.dp),
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Default,
            )

        )
        Column(modifier = modifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                focusRequester.captureFocus()
                isOpen.value = true
            }
            .fillMaxWidth()
            .background(
                color = if (enabled) TextFieldDefaults.colors().unfocusedContainerColor.copy(
                    alpha = 0.4f
                ) else TextFieldDefaults.colors().disabledContainerColor
            )
            .padding(TextFieldDefaults.contentPaddingWithoutLabel())) {
            if (isOpen.value) {
                DatePickerDialog(onDismissRequest = { isOpen.value = false }, confirmButton = {
                    Row(Modifier.padding(horizontal = 16.dp)) {
                        TextButton(onClick = { isOpen.value = false }) {
                            Text(text = stringResource(R.string.cancel))
                        }
                        TextButton(onClick = {
                            isOpen.value = false
                            if (onChange != null && datePickerState.selectedDateMillis != null) {
                                onChange(Date(datePickerState.selectedDateMillis!!))
                            }
                        }) {
                            Text(text = stringResource(R.string.defined))
                        }
                    }
                }) {
                    DatePicker(state = datePickerState,showModeToggle = false)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space = 12.dp)) {
                leadingIcon?.invoke()
                Row(modifier = Modifier.weight(weight = 1f)) {
                    if (value != null) {
                        Text(
                            text = df.format(value),
                        )
                    } else {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                color = if (enabled) TextFieldDefaults.colors().unfocusedPlaceholderColor else TextFieldDefaults.colors().disabledPlaceholderColor,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.W400,
                                fontFamily = FontFamily.Default,
                            )
                        )
                    }
                }
                trailingIcon?.invoke()
            }
        }
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }


}


@Preview
@Composable
private fun DateFieldPreview() {
    DateField(
        value = Date(),
        placeholder = "Date",
        label = "Now"
    )
}