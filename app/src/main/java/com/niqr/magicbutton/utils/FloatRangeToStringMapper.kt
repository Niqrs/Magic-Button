package com.niqr.magicbutton.utils

import kotlin.math.roundToInt

fun ClosedFloatingPointRange<Float>.toRoundedString() =
    "${this.start.roundToInt()}-${this.endInclusive.roundToInt()}"