package com.plcoding.core.designsystem.components.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import com.plcoding.core.designsystem.theme.LocalDimensions

@Composable
fun ChirpDialogContent(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val dimensions = LocalDimensions.current
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensions.dimen16)),
            color = MaterialTheme.colorScheme.surface
        ) {
            content()
        }
    }
}