package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectField(
    modifier: Modifier = Modifier,
    values: List<T>,
    value: T? = null,
    placeholder: String,
    label: String,
    enabled: Boolean = true,
    heightFraction: Float = 0.5f,
    onChange: ((value: T) -> Unit)? = null,
    key: (value: T) -> String,
    modelValue: (value: T) -> String,
    filter: ((value: T, query: String) -> Boolean)? = null,
    modelItem: @Composable (T) -> Unit
) {

    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    val bottomColor =
        if (isFocused) TextFieldDefaults.colors().focusedIndicatorColor else TextFieldDefaults.colors().unfocusedIndicatorColor


    var query by remember {
        mutableStateOf<String>("")
    }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
            .clip(shape = TextFieldDefaults.shape)
            .clickable(
                enabled = enabled, indication = null, interactionSource = interactionSource
            ) {
                showBottomSheet = true
                focusRequester.captureFocus()
            }
            .fillMaxWidth()
            .background(color = if (enabled) TextFieldDefaults.colors().unfocusedContainerColor else TextFieldDefaults.colors().disabledContainerColor)
            .drawBehind {
                drawLine(
                    start = Offset.Zero.copy(y = size.height - TextFieldDefaults.UnfocusedIndicatorThickness.toPx()),
                    end = Offset(
                        x = size.width,
                        y = size.height - TextFieldDefaults.UnfocusedIndicatorThickness.toPx()
                    ),
                    color = bottomColor,
                    strokeWidth = if (isFocused) TextFieldDefaults.FocusedIndicatorThickness.toPx() else TextFieldDefaults.UnfocusedIndicatorThickness.toPx()
                )
            }
            .padding(TextFieldDefaults.contentPaddingWithoutLabel())) {

            Box {
                Text(
                    modifier = Modifier.offset(y = (-9).dp),
                    text = label,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = if (enabled) (if (isFocused) TextFieldDefaults.colors().focusedLabelColor else TextFieldDefaults.colors().unfocusedLabelColor) else TextFieldDefaults.colors().disabledLabelColor
                    )
                )
                Row(
                    modifier = Modifier
                        .offset(y = if (value != null) 5.dp else 8.dp)
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    if (value != null) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = modelValue(value),
                            style = TextStyle(
                                color = if (enabled) LocalContentColor.current else TextFieldDefaults.colors().disabledTextColor
                            )
                        )
                    } else {
                        Text(
                            modifier = Modifier.weight(1f), text = placeholder, style = TextStyle(
                                color = if (enabled) TextFieldDefaults.colors().unfocusedPlaceholderColor else TextFieldDefaults.colors().disabledPlaceholderColor
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "KeyboardArrowDown",
                        tint = if (enabled) LocalContentColor.current else TextFieldDefaults.colors().disabledLabelColor
                    )
                }
            }
        }
        if (showBottomSheet) {

            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(heightFraction), onDismissRequest = {
                    showBottomSheet = false
                }, sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    if (filter != null) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            OutlinedTextField(
                                value = query,
                                placeholder = {
                                    Text(text = stringResource(R.string.app_name))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = { qry ->
                                    query = qry
                                },
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp),
                    ) {
                        items(
                            items = if (filter != null) values.filter { item ->
                                filter(
                                    item, query.trim()
                                )
                            } else values,
                            key = { item -> key(item) },
                        ) { item ->

                            Row(modifier = Modifier
                                .animateItem()
                                .clip(shape = RoundedCornerShape(percent = 2))
                                .clickable {
                                    onChange?.let { it(item) }
                                    scope
                                        .launch { sheetState.hide() }
                                        .invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                }
                                .fillMaxWidth()) {
                                modelItem(item)
                            }
                        }
                    }
                }
            }
        }
    }
}


