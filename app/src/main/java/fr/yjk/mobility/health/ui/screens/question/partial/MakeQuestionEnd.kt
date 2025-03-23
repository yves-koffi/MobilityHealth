package fr.yjk.mobility.health.ui.screens.question.partial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.OtpRequest
import fr.yjk.mobility.health.ui.components.CustomButton
import fr.yjk.mobility.health.ui.components.CustomTextField
import fr.yjk.mobility.health.ui.components.MultipleQuestionField
import fr.yjk.mobility.health.ui.components.OptionQuestionField
import fr.yjk.mobility.health.ui.components.TextQuestionField
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import fr.yjk.mobility.health.utils.extensions.FailureType
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun MakeQuestionEnd(fields: List<QuestionGroup>, onValueChange: (List<QuestionGroup>) -> Unit) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (field in fields) {
                for (question in field.questions) {
                    key(question.ref + question.type) {
                        if (question.type == "text") {
                            TextQuestionField(question = question, onValueChange = { qst ->
                                onValueChange(fields.map { group ->
                                    if (group.ref == field.ref) {
                                        group.copy(questions = group.questions.map { qt ->
                                            if (qt.text?.ref == qst.text?.ref) {
                                                qst
                                            } else {
                                                qt
                                            }
                                        })
                                    } else group
                                })
                            })
                        }
                        if (question.type == "multiple") {
                            MultipleQuestionField(question = question, onValueChange = { qst ->
                                onValueChange(fields.map { group ->
                                    if (group.ref == field.ref) {
                                        group.copy(questions = group.questions.map { qt ->
                                            if (qt.multiple?.ref == qst.multiple?.ref) {
                                                qst
                                            } else {
                                                qt
                                            }
                                        })
                                    } else group
                                })
                            })
                        }
                        if (question.type == "option") {
                            OptionQuestionField(question = question, onValueChange = { qst ->
                                onValueChange(fields.map { group ->
                                    if (group.ref == field.ref) {
                                        group.copy(questions = group.questions.map { qt ->
                                            if (qt.option?.ref == qst.option?.ref) {
                                                qst
                                            } else {
                                                qt
                                            }
                                        })
                                    } else group
                                })
                            })
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun MakeDirectoryLastStepPreview() {
    MobilityHealthTheme {
        //MakeQuestionEnd()
    }
}