package com.uam.aplicationdemo.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uam.aplicationdemo.tools.evaluate

@Composable
fun CalculatorScreen() {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }
    val buttons = listOf(
        listOf("C","(",")","÷"),
        listOf("7","8","9","*"),
        listOf("4","5","6","-"),
        listOf("1","2","3","+"),
        listOf("0",".","=","")
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text=expression,
            fontSize = 36.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Text( text = result,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )
        for (row in buttons) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly)
            {
               for (button in row) {
                   if (button.isNotEmpty()){
                       CalculatorButton(symbol = button){
                           when(button) {
                               "=" -> {
                                   result = evaluate(expression).toString()
                               }
                               "C" -> {
                                   expression = ""
                                   result = ""
                               }
                               else -> {
                                   expression += button
                               }
                           }
                       }
                   }
                   else {
                       Spacer(modifier = Modifier.size(80.dp))
                   }
               }
            }
        }
    }
}

@Composable
fun CalculatorButton(symbol: String,onClick : ()-> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
            .size(80.dp)
            .background(Color.Gray,RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ){
        Text(text = symbol,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}