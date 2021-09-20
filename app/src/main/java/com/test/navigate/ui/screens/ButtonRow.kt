package com.test.navigate.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonRow(
    texts: List<String>,
    weights: List<Float>,
    onButtonClick: (text: String) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in texts.indices) {
            Button(
                modifier = Modifier.weight(weights[i])
                    .padding(4.dp),
                onClick = {
                    onButtonClick(texts[i])
                }
            ) {
                Text(
                    text = texts[i],
                    fontSize = 24.sp
                )
            }
        }
    }
}