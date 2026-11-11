package com.plcoding.chat.presentation.chat_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.plcoding.core.designsystem.theme.ChirpTheme
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ChatListRoot(
    //viewModel: ChatListViewModel = viewModel()
) {
    //val state by viewModel.state.collectAsStateWithLifecycle()

    ChatListScreen(
        state = ChatListState(),
        onAction = {}
    )
}

@Composable
fun ChatListScreen(
    state: ChatListState,
    onAction: (ChatListAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat list screen")
    }

}

@Preview
@Composable
private fun Preview() {
    ChirpTheme {
        ChatListScreen(
            state = ChatListState(),
            onAction = {}
        )
    }
}

@Serializable
data object ChatListRoute