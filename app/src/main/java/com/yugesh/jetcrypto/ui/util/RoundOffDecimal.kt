package com.yugesh.jetcrypto.ui.util

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(
    decimalFormat: DecimalFormat = DecimalFormat("#.##")
): Double {
    decimalFormat.roundingMode = RoundingMode.CEILING
    return decimalFormat.format(this).toDouble()
}