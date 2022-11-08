package com.niqr.magicbutton.data.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt
import kotlin.random.Random

@Serializable
class MagickColorGenerator(
    private val redRange: Pair<Int, Int> = Pair(0, 256),
    private val greenRange: Pair<Int, Int> = Pair(0, 256),
    private val blueRange: Pair<Int, Int> = Pair(0, 256)
) {
    constructor(
        redRange: ClosedFloatingPointRange<Float>,
        greenRange: ClosedFloatingPointRange<Float>,
        blueRange: ClosedFloatingPointRange<Float>
    ) : this(
        redRange = Pair(redRange.start.roundToInt(), redRange.endInclusive.roundToInt() + 1),
        greenRange = Pair(greenRange.start.roundToInt(), greenRange.endInclusive.roundToInt() + 1),
        blueRange = Pair(blueRange.start.roundToInt(), blueRange.endInclusive.roundToInt() + 1),
    )

    fun generateColor() = Color(
        Random.nextInt(redRange.first, redRange.second),
        Random.nextInt(greenRange.first, greenRange.second),
        Random.nextInt(blueRange.first, blueRange.second),
    )
}