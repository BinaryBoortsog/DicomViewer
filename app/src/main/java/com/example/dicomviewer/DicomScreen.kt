package com.example.dicomviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.navigation.NavHostController
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Text(
                text = "DICOM Viewer",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Lazy Grid with Clickable Items
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                content = {
                    items(100) { index -> // Generates 100 items
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .aspectRatio(1f) // Makes the items square
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color.Green)
                                .clickable {
                                    // Navigate to the details screen on item click
                                    navController.navigate("itemDetail/$index")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Item $index",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ItemDetailScreen(itemId: String) {
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
                text = "Item Details: $itemId",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Item Image (This could be a placeholder or actual image )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Item Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Item Description
            Text(
                text = "This is a detailed description of the item with ID: $itemId. It contains all the necessary information like usage, features, etc.",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Details ( buttons, price, etc.)
            Button(
                onClick = { /* Handle any action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "View More", color = Color.White)
            }
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
