package com.test.navigate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.navigate.ui.routes.NavigationScreens
import com.test.navigate.ui.routes.navigate

@Composable
fun HomeScreen(
    navController: NavController
) {
    val input = remember { mutableStateOf("") }
    val lastOperation = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = input.value,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            style = MaterialTheme.typography.body1,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonRow(
            listOf("7", "8", "9", "CE"),
            listOf(0.25f, 0.25f, 0.25f, 0.25f)
        ) { number ->
            if (number == "CE") {
                input.value = ""
                lastOperation.value = ""
            } else {
                input.value = input.value.plus(number)
            }
        }
        ButtonRow(
            listOf("4", "5", "6", "-"),
            listOf(0.25f, 0.25f, 0.25f, 0.25f)
        ) { number ->
            input.value = input.value.plus(number)
            if (number == "-") {
                lastOperation.value = "-"
            }
        }
        ButtonRow(
            listOf("1", "2", "3", "+"),
            listOf(0.25f, 0.25f, 0.25f, 0.25f)
        ) { number ->
            input.value = input.value.plus(number)
            if (number == "+") {
                lastOperation.value = "+"
            }
        }
        ButtonRow(
            listOf("0", "="),
            listOf(0.5f, 0.5f)
        ) { number ->
            if (number == "=") {
                if (lastOperation.value == "+") {
                    val numbers = split(input.value, lastOperation.value)
                    navigate(
                        "first/${numbers.first}",
                        "second/${numbers.second}",
                        NavigationScreens.Addition,
                        navController
                    )
                } else if (lastOperation.value == "-") {
                    val numbers = split(input.value, lastOperation.value)
                    navigate(
                        "first/${numbers.first}",
                        "second/${numbers.second}",
                        NavigationScreens.Subtraction,
                        navController
                    )
                }
            } else {
                input.value = input.value.plus(number)
            }
        }
    }
}

private fun split(input: String, split: String): Pair<String, String> {
    return Pair(input.substringBefore(split), input.substringAfter(split))
}
