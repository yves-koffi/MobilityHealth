package fr.yjk.mobility.health.fake

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import fr.yjk.mobility.health.R

data class PaymentMethod(
    val key: String,
    @StringRes val name: Int,
    @DrawableRes val resource: Int,
) {
    companion object {
        val items: List<PaymentMethod> = listOf(
            PaymentMethod(
                key = "p1",
                name = R.string.p1,
                resource = R.drawable.p1
            ),
            PaymentMethod(
                key = "p2",
                name = R.string.p2,
                resource = R.drawable.p2
            ),
            PaymentMethod(
                key = "p3",
                name = R.string.p3,
                resource = R.drawable.p3
            ),
            PaymentMethod(
                key = "p4",
                name = R.string.p4,
                resource = R.drawable.p4
            ),
            PaymentMethod(
                key = "p5",
                name = R.string.p5,
                resource = R.drawable.p5
            ),
            PaymentMethod(
                key = "p6",
                name = R.string.p6,
                resource = R.drawable.p6
            )
        )
    }
}