package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen()
                }
            }
        }
    }
}

@Composable
fun BMIScreen() {
    var name:String by remember { mutableStateOf("") }
    var pes:Int by remember { mutableStateOf(80) }
    var alcada:Int by remember { mutableStateOf(180) }
    var bmi:Float by remember { mutableStateOf(0f) }
    Column() {
        Text(text = "BMI Calculator",
           )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") }
        )
        Text(text = "Pes")
        Row() {
            Text(text = "$pes Kg")
            Button(onClick = {
                pes--
            }) {
                Text(text = "-")
            }
            Button(
                onClick = { pes++ },
            ) {
                Text(
                    text = "+"
                )
            }
        }
        Text(text = "Alcada: $alcada cm")
        Slider(
            value = alcada.toFloat(),
            onValueChange = { alcada = it.toInt() },
            valueRange = 100f..250f
        )
        Button(
            onClick = {
                var alcadaMetres:Float = alcada.toFloat() / 100
                bmi = (pes / (alcadaMetres * alcadaMetres))
            }
        ) {
            Text(text = "Calcular BMI")
        }
        if(bmi != 0f) {
            Text(text = "El teu BMI es  $bmi")
        }

    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "hola $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}