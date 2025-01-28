package com.henil.dogimagegen.ui.screen.genratescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henil.dogimagegen.navigation.GlobalNavController
import com.henil.dogimagegen.ui.component.TopBar
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.henil.dogimagegen.R
import com.henil.dogimagegen.theme.AppTheme
import com.henil.dogimagegen.ui.component.AppButton

@Composable
fun GenerateScreen(
    viewModel: GenerateImageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    GenerateScreenContent(uiState = uiState, onEvent = viewModel::onEvent)
}

@Composable
fun GenerateScreenContent(
    uiState: GenerateImageUiState,
    onEvent: (GenerateImageUiEvent) -> Unit
) {
    val globalNavController = GlobalNavController.current
    Scaffold(
        topBar = {
            TopBar(onBackClick = { globalNavController.popBackStack() })
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Box(
                    Modifier.align(Alignment.Center),
                ) {

                    when {
                        uiState.imageUrl.isEmpty() && uiState.isLoading -> {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
                        }

                        uiState.imageUrl.isEmpty() && !uiState.isLoading -> {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(bottom = 200.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_image),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    "Click Button to\nGenerate Image",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }

                        else -> {
                            DogImage(uiState.imageUrl)
                        }
                    }
                }
                AppButton(
                    onClick = {
                        onEvent(GenerateImageUiEvent.GenerateImage)
                    },
                    text = "Generate",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 50.dp),
                )
            }

        })
}

@Composable
fun DogImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url) // URL of the image
            .crossfade(true) // Enables crossfade animation
            .build(),
        contentDescription = "Dog Image",
        modifier = Modifier.wrapContentSize(), // Modifier for the image layout
        contentScale = ContentScale.Crop // Crops or scales the image
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GenerateScreenPreview() {
    AppTheme {
        GenerateScreen()
    }
}