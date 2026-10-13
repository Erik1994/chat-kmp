package com.plcoding.core.designsystem.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plcoding.core.designsystem.components.brand.ChirpSuccessIcon
import com.plcoding.core.designsystem.components.buttons.ChirpButton
import com.plcoding.core.designsystem.components.buttons.ChirpButtonStyle
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChirpSimpleSuccessLayout(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    primaryButton: @Composable () -> Unit,
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    val dimensions = LocalDimensions.current
    Column(
        modifier = modifier
            .padding(dimensions.dimen16),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -(25).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.extended.textPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensions.dimen8))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.extended.textSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensions.dimen24))
            primaryButton()
            secondaryButton?.let {
                Spacer(modifier = Modifier.height(dimensions.dimen8))
                it()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChirpSimpleSuccessLayoutLightPreview() {
    ChirpTheme {
        ChirpSimpleSuccessLayout(
            modifier = Modifier.fillMaxWidth(),
            title = "Registration successful!",
            description = "You can now log in to your account.",
            icon = {
                ChirpSuccessIcon()
            },
            primaryButton = {
                ChirpButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Log in",
                    onClick = {}
                )
            },
            secondaryButton = {
                ChirpButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Resend verification email",
                    onClick = {},
                    style = ChirpButtonStyle.SECONDARY
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = false)
fun ChirpSimpleSuccessLayoutDarkPreview() {
    ChirpTheme(darkTheme = true) {
        ChirpSimpleSuccessLayout(
            modifier = Modifier.fillMaxWidth(),
            title = "Registration successful!",
            description = "You can now log in to your account.",
            icon = {
                ChirpSuccessIcon()
            },
            primaryButton = {
                ChirpButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Log in",
                    onClick = {}
                )
            },
            secondaryButton = {
                ChirpButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Resend verification email",
                    onClick = {},
                    style = ChirpButtonStyle.SECONDARY
                )
            }
        )
    }
}