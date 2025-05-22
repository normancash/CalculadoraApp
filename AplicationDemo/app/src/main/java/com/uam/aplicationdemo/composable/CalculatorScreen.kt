package com.uam.aplicationdemo.composable

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material3.DropdownMenu
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

@SuppressLint("DefaultLocale")
fun evaluador(exp:String):String {
    return try {
        val eval = evaluate(exp)
        if (eval % 1.0 == 0.0) eval.toInt().toString()
        else String.format("%.4f",eval)
    }
    catch(e:Exception) {
        "error"
    }
}

@Composable
fun CalculatorScreen() {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }
    val buttons = listOf(
        listOf("C","(",")","<-"),
        listOf("7","8","9","÷"),
        listOf("4","5","6","*"),
        listOf("1","2","3","-"),
        listOf("0",".","=","+")
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
                                   result = evaluador(expression)
                               }
                               "C" -> {
                                   expression = ""
                                   result = ""
                               }
                               "<-" -> {
                                   expression = expression.dropLast(1)
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
    val backgroudcolor by animateColorAsState(
        targetValue = when (symbol) {
            "C" -> Color(0xFFFF6B6B)
            "=","+","-","*","÷" -> Color(0xFF4D96FF)
            else -> Color(0xFFE0E0E0)
        },label = "buttonColor"
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
            .size(80.dp)
            .background(backgroudcolor,RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ){
        Text(text = symbol,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}