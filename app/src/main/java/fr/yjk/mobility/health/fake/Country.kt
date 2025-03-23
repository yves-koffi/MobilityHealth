package fr.yjk.mobility.health.fake

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import fr.yjk.mobility.health.R


data class Country(
    val key: String,
    @StringRes val name: Int,
    @DrawableRes val resource: Int,
) {
    companion object {
        val items: List<PaymentMethod> = listOf(
            PaymentMethod(
                key = "ci",
                name = R.string.ci,
                resource = R.drawable.ci
            ),

            PaymentMethod(
                key = "tr",
                name = R.string.tu,
                resource = R.drawable.tu
            ),
            PaymentMethod(
                key = "cn",
                name = R.string.cn,
                resource = R.drawable.cn
            ),

            PaymentMethod(
                key = "gh",
                name = R.string.gh,
                resource = R.drawable.gh
            ),
        )
        val NationalityItems: List<PaymentMethod> = listOf(
            PaymentMethod(
                key = "ci",
                name = R.string.ci_n,
                resource = R.drawable.ci
            ),
            PaymentMethod(
                key = "tr",
                name = R.string.tu_n,
                resource = R.drawable.tu
            ),
            PaymentMethod(
                key = "cn",
                name = R.string.cn_n,
                resource = R.drawable.cn
            ),
            PaymentMethod(
                key = "gh",
                name = R.string.gh_n,
                resource = R.drawable.gh
            ),
        )
    }
}