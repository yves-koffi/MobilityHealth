package fr.yjk.mobility.health.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.model.Question

@Composable
fun TextQuestionField(question: Question, onValueChange: (Question) -> Unit) {
    CustomTextField(
        label = question.text?.label ?: "",
        value = question.text?.value ?: "",
        placeholder = "",
        onValueChange = {
            print("======================>>")
            onValueChange(question.copy(text = question.text?.copy(value = it)))
        },
        modifier = Modifier.fillMaxWidth(),
    )
}


@Composable
fun MultipleQuestionField(question: Question, onValueChange: (Question) -> Unit) {
    Column {
        Text(text = question.multiple?.label ?: "", style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Default,
        ))
        Column(modifier = Modifier.padding(start = 2.dp)) {
            for ((index, option) in (question.multiple?.response ?: emptyList()).withIndex()) {
                key(option.ref) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = MaterialTheme.colorScheme.outline
                            ),
                            checked = option.value == "1",
                            onCheckedChange = { checked ->
                                onValueChange(question.copy(multiple = question.multiple?.copy(response = question.multiple.response.map { item ->
                                    if (item.ref == option.ref) {
                                        item.copy(value = if (checked) "1" else "0")
                                    } else {
                                        item
                                    }
                                })))
                            })
                        Text(option.label, style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily.Default,
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun OptionQuestionField(question: Question, onValueChange: (Question) -> Unit) {
    Column {
        Text(text = question.option?.label ?: "", style =TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Default,
        ))
        Row {
            for ((index, option) in (question.option?.response ?: emptyList()).withIndex()) {
               key(option.ref) {
                   Row(
                       horizontalArrangement = Arrangement.spacedBy(2.dp),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       RadioButton(selected = option.value == "1", onClick = {
                           onValueChange(question.copy(option = question.option?.copy(response = question.option.response.map { item ->
                               if (item.ref == option.ref) {
                                   item.copy(value = "1")
                               } else {
                                   item.copy(value = "0")
                               }
                           })))
                       })
                       Text(option.label, style = TextStyle(
                           fontSize = 14.sp,
                           fontWeight = FontWeight.W400,
                           fontFamily = FontFamily.Default,
                       ))
                   }
               }
            }
        }
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for ((index, option) in (question.option?.response ?: emptyList()).withIndex()) {
                if (option.value == "1" && option.result.isNotEmpty()) {
                    if (option.type == "text") {
                        for ((index, result) in option.result.withIndex()) {
                            key(result.ref) {
                                CustomTextField(
                                    label = result.label,
                                    value = result.value,
                                    placeholder = "",
                                    onValueChange = { text ->
                                        onValueChange(
                                            question.copy(
                                                option = question.option?.copy(
                                                    response = question.option.response.map { resp ->
                                                        if (resp.ref == option.ref) {
                                                            resp.copy(result = resp.result.map { res ->
                                                                if (res.ref == result.ref) {
                                                                    res.copy(value = text)
                                                                } else res
                                                            })
                                                        } else resp
                                                    })
                                            )
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    } else {
                        for ((index, result) in option.result.withIndex()) {
                            key(result.ref) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = result.value.isNotEmpty() && result.value == "1",
                                        onClick = {
                                            onValueChange(
                                                question.copy(
                                                    option = question.option?.copy(
                                                        response = question.option.response.map { resp ->
                                                            resp.copy(result = resp.result.map { res ->

                                                                if (res.ref == result.ref) {
                                                                    res.copy(value = "1")
                                                                } else res.copy(value = "0")
                                                            })
                                                        })
                                                )
                                            )
                                        })
                                    Text(
                                        result.label,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.W400,
                                            fontFamily = FontFamily.Default,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


    }
}