package com.henil.dogimagegen.ui.screen.imagescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.decode.ImageSource
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.henil.dogimagegen.R
import com.henil.dogimagegen.navigation.GlobalNavController
import com.henil.dogimagegen.theme.AppTheme
import com.henil.dogimagegen.ui.component.AppButton
import com.henil.dogimagegen.ui.component.TopBar

@Composable
fun ImageScreen(
    viewModel: ImageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ImageScreenContent(uiState = uiState, onEvent = viewModel::onEvent)
}

@Composable
fun ImageScreenContent(
    uiState: ImageUiState,
    onEvent: (ImageUiEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(ImageUiEvent.GetAllImages)
    }
    var globalNavController = GlobalNavController.current
    Scaffold(
        topBar = {
            TopBar { globalNavController.popBackStack() }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(items = uiState.listOfImageUrls) { dogEntity ->
                        ImageCard(dogEntity)
                    }
                }

                AppButton(
                    onClick = {
                        onEvent(ImageUiEvent.ClearAllImage)
                    },
                    text = "Clear dogs",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 50.dp),
                )
            }
        },
    )
}

@Composable
fun ImageCard(url: String) {
    Card(
        modifier = Modifier
            .padding(2.dp), shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder), // Add a placeholder
            error = painterResource(id = R.drawable.error) // Add an error image
        )
    }
}

@Composable
@Preview
fun ImageScreenPreview() {
    AppTheme {
        ImageScreenContent(
            uiState = ImageUiState(),
            onEvent = {}
        )
    }
}