package com.henil.dogimagegen.ui.component

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.henil.dogimagegen.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(title = {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dog Image Generator", style = MaterialTheme.typography.titleMedium)
        }
    },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun AppButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    ElevatedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
        modifier = modifier
    ) {
        Text(text)
    }
}