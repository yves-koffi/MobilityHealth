package fr.yjk.mobility.health.model

import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class QuestionGroup(
    val ref: String,
    val title: String,
    val page: String,
    val questions: List<Question>
) {
    fun validate(): Boolean {
        return questions.all { it.validate() }
    }
}

@Immutable
@Serializable
data class Question(
    val ref: String,
    val type: String,
    val text: Text? = null,
    val multiple: Multiple? = null,
    val option: Option? = null
) {

    fun validate(): Boolean {
        return validateText() || validateMultiple() || validateOption()
    }

    private fun validateText(): Boolean {
        return type == "text" && text?.value?.isNotEmpty() == true
    }

    private fun validateMultiple(): Boolean {
        return type == "multiple" && multiple?.response?.any { it.value == "1" } == true
    }

    private fun validateOption(): Boolean {
        return type == "option" && option?.response?.any { it.value == "1" && (it.type == "option" && it.result.any { it.value == "1" } || it.type == "text" && it.result.all { it.value.isNotEmpty() }) } == true
    }
}

@Immutable
@Serializable
data class Answer(
    val ref: String,
    val label: String,
    val value: String,
    val type: String? = null,
    val result: List<Answer> = emptyList()
)

@Immutable
@Serializable
data class Text(
    val ref: String,
    val label: String,
    val value: String = ""
)

@Immutable
@Serializable
data class Multiple(
    val ref: String,
    val label: String,
    val response: List<Answer> = emptyList()
)

@Immutable
@Serializable
data class Option(
    val ref: String,
    val label: String,
    val response: List<OptionAnswer> = emptyList()
)

@Immutable
@Serializable
data class OptionAnswer(
    val ref: String,
    val label: String,
    val value: String = "",
    val type: String,
    val result: List<Answer> = emptyList()
)