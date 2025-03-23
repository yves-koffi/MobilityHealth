package fr.yjk.mobility.health.utils.extensions

import java.text.Normalizer
import java.text.NumberFormat
import java.util.Locale

fun String.normalize(): String {
    val input = Normalizer.normalize(this, Normalizer.Form.NFD)

    return input.replace("(\\p{M}|[^a-z\\d\\s])".toRegex(), "")
}

fun Int.currencyFormat(): String = NumberFormat.getCurrencyInstance(
    Locale(
        Locale.getDefault().language,
        "CI"
    )
)
    .format(this)