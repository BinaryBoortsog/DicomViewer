package com.example.dicomviewer


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.ui.platform.LocalContext

import androidx.activity.result.contract.ActivityResultContracts.GetContent

@Composable
fun HomeScreen() {
    // Access context and ActivityResultLauncher to pick files
    val context = LocalContext.current
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                // Handle the selected file URI (e.g., display its path or read its contents)
                // For example: Log the URI or show it in the UI
                println("Selected file URI: $uri")
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "import your DICOM file",
                color = Color.White, // Set text color for visibility
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    // Open the file picker when the button is clicked
                    filePickerLauncher.launch("application/dicom") // You can change MIME type if needed
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Button background color
                    contentColor = Color.White // Button text/icon color
                ),
                shape = CircleShape, // Circular shape
                modifier = Modifier
                    .size(80.dp) // Set equal width and height for a circular button
                    .border(
                        width = 1.dp,
                        color = Color.White, // Lightened border color
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "+",
                    color = Color.White, // Set text color for visibility
                    fontSize = 40.sp, // Adjust font size using sp
                    fontWeight = FontWeight.Light // Optional for styling
                )
            }
        }
    }
}
