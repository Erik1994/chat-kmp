package com.plcoding.core.designsystem.components.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plcoding.core.designsystem.components.brand.ChirpBrandLogo
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.designsystem.theme.extended
import com.plcoding.core.presentation.util.DeviceConfiguration
import com.plcoding.core.presentation.util.currentDeviceConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChirpAdaptiveFormLayout(
    modifier: Modifier = Modifier,
    headerText: String,
    errorText: String? = null,
    logo: @Composable () -> Unit,
    formContent: @Composable ColumnScope.() -> Unit,
) {
    val dimensions = LocalDimensions.current
    val configuration = currentDeviceConfiguration()
    val headerColor = if (configuration == DeviceConfiguration.MOBILE_LANDSCAPE) {
        MaterialTheme.colorScheme.onBackground
    } else {
        MaterialTheme.colorScheme.extended.textPrimary
    }

    when (configuration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            ChirpSurface(
                modifier = modifier
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .consumeWindowInsets(WindowInsets.displayCutout),
                header = {
                    Spacer(modifier = Modifier.height(dimensions.dimen32))
                    logo()
                    Spacer(modifier = Modifier.height(dimensions.dimen32))
                }
            ) {
                Spacer(modifier = Modifier.height(dimensions.dimen24))
                AuthHeaderSection(
                    headerText = headerText,
                    headerColor = headerColor,
                    errorText = errorText
                )
                Spacer(modifier = Modifier.height(dimensions.dimen24))
                formContent()
            }
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .consumeWindowInsets(WindowInsets.displayCutout)
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .padding(start = dimensions.dimen32),
                horizontalArrangement = Arrangement.spacedBy(dimensions.dimen16),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimensions.dimen24)
                ) {
                    Spacer(modifier = Modifier.height(dimensions.dimen16))
                    logo()
                    AuthHeaderSection(
                        headerText = headerText,
                        headerColor = headerColor,
                        errorText = errorText,
                        headerTextAlignment = TextAlign.Start
                    )
                }
                ChirpSurface(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Spacer(modifier = Modifier.height(dimensions.dimen16))
                    formContent()
                    Spacer(modifier = Modifier.height(dimensions.dimen16))
                }
            }
        }
        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(dimensions.dimen32),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensions.dimen32)
            ) {
                logo()
                Column(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(dimensions.dimen32))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            horizontal = dimensions.dimen24,
                            vertical = dimensions.dimen32
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AuthHeaderSection(
                        headerText = headerText,
                        headerColor = headerColor,
                        errorText = errorText
                    )
                    formContent()
                }
            }
        }
    }
}

@Composable
fun ColumnScope.AuthHeaderSection(
    headerText: String,
    headerColor: Color,
    errorText: String? = null,
    headerTextAlignment: TextAlign = TextAlign.Center
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = headerText,
        style = MaterialTheme.typography.titleLarge,
        color = headerColor,
        textAlign = headerTextAlignment
    )
    AnimatedVisibility(
        errorText != null
    ) {
        errorText?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                textAlign = headerTextAlignment
            )
        }
    }
}

@Composable
@Preview
fun ChirpAdaptiveFormLayoutLightPreview() {
    ChirpTheme {
        ChirpAdaptiveFormLayout(
            headerText = "Welcome to Chirp!",
            errorText = "Login failed",
            logo = { ChirpBrandLogo() },
            formContent = {
                Text(
                    text = "Sample form title 1",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}

@Composable
@Preview
fun ChirpAdaptiveFormLayoutDarkPreview() {
    ChirpTheme(darkTheme = true) {
        ChirpAdaptiveFormLayout(
            headerText = "Welcome to Chirp!",
            errorText = "Login failed",
            logo = { ChirpBrandLogo() },
            formContent = {
                Text(
                    text = "Sample form title 1",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}