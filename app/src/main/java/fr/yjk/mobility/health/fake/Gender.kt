package fr.yjk.mobility.health.fake

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import fr.yjk.mobility.health.R

data class Gender(
    val key: String,
    @StringRes val name: Int,
    @DrawableRes val resource: Int,
) {
    companion object {
        val items: List<Gender> = listOf(
            Gender(
                key = "m",
                name = R.string.m,
                resource = R.drawable.gender
            ),
            Gender(
                key = "mm",
                name = R.string.mm,
                resource = R.drawable.gender
            ),
            Gender(
                key = "md",
                name = R.string.md,
                resource = R.drawable.gender
            ),
        )
    }
}