package com.plcoding.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val dimen0: Dp = 0.dp,
    val dimen1: Dp = 1.dp,
    val dimen2: Dp = 2.dp,
    val dimen3: Dp = 3.dp,
    val dimen4: Dp = 4.dp,
    val dimen6: Dp = 6.dp,
    val dimen8: Dp = 8.dp,
    val dimen10: Dp = 10.dp,
    val dimen11: Dp = 11.dp,
    val dimen12: Dp = 12.dp,
    val dimen15: Dp = 15.dp,
    val dimen16: Dp = 16.dp,
    val dimen18: Dp = 18.dp,
    val dimen20: Dp = 20.dp,
    val dimen24: Dp = 24.dp,
    val dimen25: Dp = 25.dp,
    val dimen30: Dp = 30.dp,
    val dimen32: Dp = 32.dp,
    val dimen35: Dp = 35.dp,
    val dimen36: Dp = 36.dp,
    val dimen40: Dp = 40.dp,
    val dimen42: Dp = 42.dp,
    val dimen45: Dp = 45.dp,
    val dimen48: Dp = 48.dp,
    val dimen50: Dp = 50.dp,
    val dimen55: Dp = 55.dp,
    val dimen60: Dp = 60.dp,
    val dimen64: Dp = 64.dp,
    val dimen70: Dp = 70.dp,
    val dimen75: Dp = 75.dp,
    val dimen80: Dp = 80.dp,
    val dimen84: Dp = 84.dp,
    val dimen88: Dp = 88.dp,
    val dimen90: Dp = 90.dp,
    val dimen92: Dp = 92.dp,
    val dimen96: Dp = 96.dp,
    val dimen100: Dp = 100.dp,
    val dimen120: Dp = 120.dp,
    val dimen500: Dp = 500.dp,
)

internal val LocalDimensions = staticCompositionLocalOf { Dimensions() }