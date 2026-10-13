package com.plcoding.core.designsystem.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.plcoding.core.designsystem.components.brand.ChirpBrandLogo
import com.plcoding.core.designsystem.theme.ChirpTheme
import com.plcoding.core.designsystem.theme.LocalDimensions
import com.plcoding.core.presentation.util.DeviceConfiguration
import com.plcoding.core.presentation.util.currentDeviceConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChirpAdaptiveResultLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = currentDeviceConfiguration()
    val dimensions = LocalDimensions.current
    Scaffold(
        modifier = modifier
    ) { innerPaddings ->
        if (configuration == DeviceConfiguration.MOBILE_PORTRAIT) {
            ChirpSurface(
                modifier = Modifier
                    .padding(innerPaddings),
                header = {
                    Spacer(modifier = Modifier.height(dimensions.dimen32))
                    ChirpBrandLogo()
                    Spacer(modifier = Modifier.height(dimensions.dimen32))
                },
                content = content
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPaddings)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = dimensions.dimen32),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensions.dimen32)
            ) {
                if (configuration != DeviceConfiguration.MOBILE_LANDSCAPE) {
                    ChirpBrandLogo()
                }
                Column(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(dimensions.dimen32))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(dimensions.dimen24)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(dimensions.dimen24),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
@Preview
fun ChirpAdaptiveResultLayoutPreview() {
    ChirpTheme {
         ChirpAdaptiveResultLayout(
             modifier = Modifier.fillMaxSize(),
             content = {
                 Text(
                     text = "Registration successful!",
                     style = MaterialTheme.typography.titleLarge,
                     color = MaterialTheme.colorScheme.onSurface
                 )
             }
         )
    }
}