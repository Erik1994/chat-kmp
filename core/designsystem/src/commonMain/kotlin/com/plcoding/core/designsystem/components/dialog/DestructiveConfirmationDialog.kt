package com.plcoding.core.designsystem.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import chirp.core.designsystem.generated.resources.Res
import chirp.core.designsystem.generated.resources.dismiss_dialog
import com.plcoding.core.designsystem.components.buttons.ChirpButton
import com.plcoding.core.designsystem.components.buttons.ChirpButtonStyle
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DestructiveConfirmationDialog(
    title: String,
    description: String,
    confirmButtonText: String,
    cancelButtonText: String,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val dimensions = LocalDimensions.current
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = dimensions.dimen24,
                    vertical = dimensions.dimen16
                )
                .widthIn(max = 480.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(dimensions.dimen15)
                )
        ) {
            Column(
                modifier = Modifier.padding(dimensions.dimen24),
                verticalArrangement = Arrangement.spacedBy(dimensions.dimen16)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.extended.textPrimary
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.extended.textSecondary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensions.dimen16),
                    horizontalArrangement = Arrangement.spacedBy(dimensions.dimen12, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ChirpButton(
                        text = cancelButtonText,
                        onClick = onCancelClick,
                        style = ChirpButtonStyle.SECONDARY
                    )
                    ChirpButton(
                        text = confirmButtonText,
                        onClick = onConfirmClick,
                        style = ChirpButtonStyle.DESTRUCTIVE_PRIMARY
                    )
                }
            }
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onDismiss
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(Res.string.dismiss_dialog),
                    tint = MaterialTheme.colorScheme.extended.textSecondary
                )
            }
        }
    }
}

@Composable
@Preview
fun DestructiveConfirmationDialogPreviewDark() {
    ChirpTheme(darkTheme = true) {
        DestructiveConfirmationDialog(
            title = "Delete profile picture?",
            description = "Thi will permanently delete your profile picture!",
            cancelButtonText = "Cancel",
            confirmButtonText = "Delete",
            onConfirmClick = {},
            onCancelClick = {},
            onDismiss = {},
        )
    }
}

@Composable
@Preview
fun DestructiveConfirmationDialogPreviewLight() {
    ChirpTheme(darkTheme = false) {
        DestructiveConfirmationDialog(
            title = "Delete profile picture?",
            description = "Thi will permanently delete your profile picture!",
            cancelButtonText = "Cancel",
            confirmButtonText = "Delete",
            onConfirmClick = {},
            onCancelClick = {},
            onDismiss = {},
        )
    }
}