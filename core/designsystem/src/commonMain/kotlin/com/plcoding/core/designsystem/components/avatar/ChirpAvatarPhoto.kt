package com.plcoding.core.designsystem.components.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class AvatarSize(val dp: Dp) {
    SMALL(40.dp), LARGE(60.dp)
}

@Composable
fun ChirpAvatarPhoto(
    modifier: Modifier = Modifier,
    displayText: String,
    size: AvatarSize = AvatarSize.SMALL,
    imageUrl: String? = null,
    textColor: Color = MaterialTheme.colorScheme.extended.textPlaceholder,
    onClick: (() -> Unit)? = null
) {
    val dimensions = LocalDimensions.current
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .clickable(
                onClick = { onClick?.invoke() },
                enabled = onClick != null
            )
            .background(MaterialTheme.colorScheme.extended.secondaryFill)
            .border(
                width = dimensions.dimen2,
                color = MaterialTheme.colorScheme.outline,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText.uppercase(),
            style = MaterialTheme.typography.titleMedium,
            color = textColor
        )
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .matchParentSize(),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun ChirpAvatarPhotoPreview() {
    ChirpTheme {
        ChirpAvatarPhoto(
            displayText = "ea",
            size = AvatarSize.LARGE
        )
    }
}