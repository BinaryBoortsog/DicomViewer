package com.example.dicomviewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun DicomScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Header
            Text(
                text = "DICOM Viewer",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Lazy Grid with Clickable DICOM items (Using placeholders here)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(10) { index -> // Generating 10 items as placeholders
                    DicomItem(index = index, navController = navController)
                }
            }
        }
    }
}

@Composable
fun DicomItem(index: Int, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(1f) // Makes the items square
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Gray)
            .clickable {
                // Navigate to the item details screen
                navController.navigate("itemDetail/$index")
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "DICOM Item $index",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ItemDetailScreen(itemId: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Item Title
            Text(
                text = "DICOM Item Details: $itemId",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Item Image (This could be an actual DICOM image or a placeholder)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "DICOM Item Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Item Description (as a placeholder for DICOM details)
            Text(
                text = "Detailed description of DICOM item with ID: $itemId. This could include metadata, patient info, etc.",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun DicomApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dicomScreen") {
        composable("dicomScreen") {
            DicomScreen(navController = navController)
        }
        composable("itemDetail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            itemId?.let {
                ItemDetailScreen(itemId = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DicomAppPreview() {
    DicomApp()
}
