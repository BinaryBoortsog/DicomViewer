package com.example.dicomviewer

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    // Access context and ActivityResultLauncher to pick files
    val context = LocalContext.current
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                // Process the file (e.g., navigate to DICOM screen with the file URI)
                val fileName = getFileNameFromUri(it, context)
                // You can pass the file URI to another screen here (DicomScreen)
                navController.navigate("dicom/${it.toString()}")
            }
        }
    )

    // Track loading state
    var isLoading by remember { mutableStateOf(false) }
    var fileName by remember { mutableStateOf<String?>(null) }

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
                text = "Import your DICOM file",
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Button to open file picker
            Button(
                onClick = {
                    // Start the file picker
                    filePickerLauncher.launch("application/dicom")
                    isLoading = true // Show loading when file picker is launched
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
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            // Display the selected file name if any
            fileName?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Selected file: $it",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

fun getFileNameFromUri(uri: Uri, context: Context): String {
    // Get the file name from the URI
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    val fileName = cursor?.getString(columnIndex ?: -1) ?: "Unknown File"
    cursor?.close()
    return fileName
}
