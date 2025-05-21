package com.uam.aplicationdemo.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpdateText(modifier:Modifier) {
    var mensaje by rememberSaveable {mutableStateOf(" mensaje nuevo")}
    var ban by rememberSaveable { mutableStateOf(true)}
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = mensaje, fontSize = 24.sp
            ,modifier = Modifier.padding(bottom = 16.dp))
        Button(
            onClick = { if (ban) {
                mensaje =  mensaje.uppercase()
                ban = false
            } else {
                mensaje = mensaje.lowercase()
                ban = true
            }
            }
        ) {
            Text("Cambiar mensaje")
        }
    }
}