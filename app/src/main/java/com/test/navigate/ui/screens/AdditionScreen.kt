package com.test.navigate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.navigate.ui.routes.NavigationScreens
import com.test.navigate.ui.viewModels.CalculatorViewModel
import com.test.navigate.ui.viewModels.Mx51ViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AdditionScreen(
    navController: NavController,
    viewModel: CalculatorViewModel,
    firstNumber: String,
    secondNumber: String
) {
    val result = (getViewModel() as CalculatorViewModel).add(firstNumber.toInt(), secondNumber.toInt())
    val status = (getViewModel() as Mx51ViewModel).getStatusText()

    Column(
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Addition Result",
            fontSize = 24.sp,
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text (
            text = " $firstNumber + $secondNumber = $result",
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate(NavigationScreens.HomeScreen.route)
            }
        ) {
            Text(
                text = "Back"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = status,
                fontSize = 24.sp,
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}