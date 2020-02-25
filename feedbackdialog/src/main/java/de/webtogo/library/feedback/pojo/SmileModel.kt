package de.webtogo.library.feedback.pojo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import de.webtogo.library.feedback.core.Rate

data class SmileModel(
    @StringRes val header: Int,
    @DrawableRes val image: Int,
    val rate : Rate,
    var isSelected : Boolean = false
)