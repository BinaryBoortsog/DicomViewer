package com.example.dicomviewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen() {
    var isDarkModeEnabled by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0.5f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // Header
            Text(
                text = "Settings",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dark Mode Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Mode",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isDarkModeEnabled,
                    onCheckedChange = { isDarkModeEnabled = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.Gray)
                )
            }

            // Slider Example (e.g., Brightness)
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                Text(
                    text = "Brightness",
                    color = Color.White,
                    fontSize = 18.sp
                )
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Gray,
                        activeTrackColor = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Current Value: ${(sliderValue * 100).toInt()}%",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            // About Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Version: 1.0.0\nDeveloped by: Dashnyam",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
