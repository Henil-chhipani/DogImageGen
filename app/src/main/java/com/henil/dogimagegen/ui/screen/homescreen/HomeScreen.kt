package com.henil.dogimagegen.ui.screen.homescreen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henil.dogimagegen.navigation.GlobalNavController
import com.henil.dogimagegen.navigation.Routes
import com.henil.dogimagegen.theme.AppTheme
import com.henil.dogimagegen.ui.component.AppButton
import com.henil.dogimagegen.ui.component.TopBar

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val globalNavController = GlobalNavController.current
    Scaffold(
        topBar = {
            TopBar(onBackClick = {
                (context as Activity).finish()
            })
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Random Dog generator",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 210.dp)
                    )

                }
                AppButton(
                    onClick = { globalNavController.navigate(Routes.Generate.route) },
                    text = "Generate",
                    modifier = Modifier
                        .padding(bottom = 70.dp)
                        .align(Alignment.BottomCenter)
                )
                AppButton(
                    onClick = { globalNavController.navigate(Routes.Images.route) },
                    text = "My Generated Images",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FirstScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}