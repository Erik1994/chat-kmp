package com.plcoding.core.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class ChirpButtonStyle {
    PRIMARY,
    DESTRUCTIVE_PRIMARY,
    SECONDARY,
    DESTRUCTIVE_SECONDARY,
    TEXT
}

@Composable
fun ChirpButton(
    modifier: Modifier = Modifier,
    text: String,
    style: ChirpButtonStyle,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val dimensions = LocalDimensions.current
    val colors = when (style) {
        ChirpButtonStyle.PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill
        )

        ChirpButtonStyle.DESTRUCTIVE_PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill
        )

        ChirpButtonStyle.SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.extended.textSecondary,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
            disabledContainerColor = Color.Transparent
        )

        ChirpButtonStyle.DESTRUCTIVE_SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.error,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
            disabledContainerColor = Color.Transparent
        )

        ChirpButtonStyle.TEXT -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
            disabledContainerColor = Color.Transparent
        )
    }

    val defaultBorderStroke = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.extended.disabledOutline
    )
    
    val border = when {
        style == ChirpButtonStyle.PRIMARY && !enabled -> defaultBorderStroke
        style == ChirpButtonStyle.SECONDARY -> defaultBorderStroke
        style == ChirpButtonStyle.DESTRUCTIVE_PRIMARY && !enabled -> defaultBorderStroke
        style == ChirpButtonStyle.DESTRUCTIVE_SECONDARY -> {
            val borderColor = if(enabled) {
                MaterialTheme.colorScheme.extended.destructiveSecondaryOutline
            } else {
                MaterialTheme.colorScheme.extended.disabledOutline
            }
            BorderStroke(
                width = 1.dp,
                color = borderColor
            )
        }
        else -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(dimensions.dimen8),
        colors = colors,
        border = border
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(dimensions.dimen15)
                    .alpha(
                        alpha = if (isLoading) 1f else 0f
                    ),
                strokeWidth = 1.5.dp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.alpha(
                    alpha = if (isLoading) 0f else 1f
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensions.dimen8,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.invoke()
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}


@Composable
@Preview
fun ChirpPrimaryButtonPreview() {
    ChirpTheme(
        darkTheme = true
    ) {
        ChirpButton(
            text = "Chirp",
            style = ChirpButtonStyle.PRIMARY,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChirpSecondaryButtonPreview() {
    ChirpTheme(

    ) {
        ChirpButton(
            text = "Chirp",
            style = ChirpButtonStyle.SECONDARY,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChirpPrimaryDestructiveButtonPreview() {
    ChirpTheme {
        ChirpButton(
            text = "Chirp",
            style = ChirpButtonStyle.DESTRUCTIVE_PRIMARY,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChirpSecondaryDestructiveButtonPreview() {
    ChirpTheme {
        ChirpButton(
            text = "Chirp",
            style = ChirpButtonStyle.DESTRUCTIVE_SECONDARY,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChirpTextButtonPreview() {
    ChirpTheme {
        ChirpButton(
            text = "Chirp",
            style = ChirpButtonStyle.TEXT,
            onClick = {}
        )
    }
}