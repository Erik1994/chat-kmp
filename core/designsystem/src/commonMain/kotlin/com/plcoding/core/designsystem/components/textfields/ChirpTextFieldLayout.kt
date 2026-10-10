package com.plcoding.core.designsystem.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended

@Composable
fun ChirpTextFieldLayout(
    modifier: Modifier = Modifier,
    title: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    onFocusChanged: (Boolean) -> Unit,
    textField: @Composable (Modifier, MutableInteractionSource) -> Unit
) {
    val dimen = LocalDimensions.current
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        onFocusChanged(isFocused)
    }

    val textFieldStyledModifier = Modifier
        .fillMaxWidth()
        .background(
            color = when {
                isFocused -> MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.05f
                )

                enabled -> MaterialTheme.colorScheme.surface
                else -> MaterialTheme.colorScheme.extended.secondaryFill
            },
            shape = RoundedCornerShape(dimen.dimen8)
        )
        .border(
            width = dimen.dimen1,
            color = when {
                isError -> MaterialTheme.colorScheme.error
                isFocused -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.outline
            },
            shape = RoundedCornerShape(dimen.dimen8)
        )
        .padding(dimen.dimen12)

    Column(
        modifier = modifier
    ) {
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.extended.textSecondary
            )
            Spacer(modifier = Modifier.height(dimen.dimen8))
        }
        textField(textFieldStyledModifier, interactionSource)
        supportingText?.let {
            Spacer(modifier = Modifier.height(dimen.dimen4))
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = if (isError) {
                    MaterialTheme.colorScheme.extended.textTertiary
                } else {
                    MaterialTheme.colorScheme.extended.textTertiary
                }
            )
        }
    }
}