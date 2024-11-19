package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                HomePageScreen()  // Start with Home Page showing only the logo
            }
        }
    }
}

@Composable
fun HomePageScreen() {
    var showForm by remember { mutableStateOf(false) }  // State to control when the form is shown

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        // Home page logo image
        Image(
            painter = painterResource(id = R.drawable.app_logo),  // Reference to app logo
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    showForm = true  // When the image is clicked, show the form
                }
        )

        // Conditionally display the form when the image is clicked
        if (showForm) {
            FormScreen()  // Show the form after the logo is clicked
        }
    }
}

@Composable
fun FormScreen() {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Heading
        Text(
            text = "User Input Form",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isError = it.length < 3
                isSubmitted = false // Reset submission status when input changes
            },
            label = { Text("Enter at least 3 characters") },
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        // Error message if input is invalid
        if (isError) {
            Text(
                text = "Input must be at least 3 characters",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // Submit button
        Button(
            onClick = {
                isSubmitted = true
                // Custom logic: print input to the log for now
                println("Submitted text: $text")
                // You can replace this with any other action (e.g., navigating to another screen)
            },
            enabled = !isError,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }

        // Success message after submission
        if (isSubmitted && !isError) {
            Text(
                text = "Form submitted successfully!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePageScreen() {
    MyApplicationTheme {
        HomePageScreen()  // Preview of the home page with\
        //
        // }

    }
}
