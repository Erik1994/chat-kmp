package com.plcoding.chat.presentation.chat_list_detail

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.presentation.util.DeviceConfiguration
import com.plcoding.core.presentation.util.currentDeviceConfiguration

@Composable
fun crateNoSpacingPaneScaffoldDirective(): PaneScaffoldDirective {
    val configuration = currentDeviceConfiguration()
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val dimensions = LocalDimensions.current

    val maxHorizontalPartitions = when(configuration) {
        DeviceConfiguration.MOBILE_PORTRAIT,
        DeviceConfiguration.MOBILE_LANDSCAPE,
        DeviceConfiguration.TABLET_PORTRAIT -> 1
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> 2
    }

    val verticalPartitionsSpacerSize: Dp
    val maxVerticalPartitions: Int

    if (windowAdaptiveInfo.windowPosture.isTabletop) {
        maxVerticalPartitions = 2
        verticalPartitionsSpacerSize = dimensions.dimen24
    } else {
        maxVerticalPartitions = 1
        verticalPartitionsSpacerSize = dimensions.dimen0
    }

    return PaneScaffoldDirective(
        maxHorizontalPartitions = maxHorizontalPartitions,
        horizontalPartitionSpacerSize = dimensions.dimen0,
        maxVerticalPartitions = maxVerticalPartitions,
        verticalPartitionSpacerSize = verticalPartitionsSpacerSize,
        defaultPanePreferredWidth = 360.dp,
        excludedBounds = emptyList()
    )
}