package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme

// Paleta de colors Cyberpunk
val CyberDarkBg = Color(0xFF090A0F)
val CyberCardBg = Color(0xFF121520)
val CyberCyan = Color(0xFF00F0FF)
val CyberPink = Color(0xFFFF007F)
val CyberYellow = Color(0xFFFFE600)
val CyberGreen = Color(0xFF00FF66)
val CyberOrange = Color(0xFFFF9900)
val CyberRed = Color(0xFFFF0055)
val CyberTextDim = Color(0xFF708090)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CyberDarkBg
                ) { innerPadding ->
                    BMIScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class CyberBMICategory(val code: String, val label: String, val color: Color)

fun getCyberBMICategory(bmi: Float): CyberBMICategory {
    return when {
        bmi < 18.5f -> CyberBMICategory("SYS_LOW_DENSITY", "SOTA PES // LOW", CyberCyan)
        bmi < 25.0f -> CyberBMICategory("SYS_NOMINAL", "PES NORMAL // OPTIMAL", CyberGreen)
        bmi < 30.0f -> CyberBMICategory("SYS_WARNING", "SOBREPÈS // HIGH", CyberOrange)
        else -> CyberBMICategory("SYS_CRITICAL", "OBESITAT // DANGER", CyberRed)
    }
}

@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var pes by remember { mutableIntStateOf(80) }
    var alcada by remember { mutableIntStateOf(180) }
    var bmi by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CyberDarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Capçalera Cyberpunk / HUD Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "[ SYSTEM // BMI_CALCULATOR_V2 ]",
                    color = CyberCyan,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "// BIOMETRIC_DIAGNOSTIC_MODULE",
                    color = CyberPink,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp
                )
            }

            // Input Nom d'Usuari Cyberpunk
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        text = "> USER_NAME_ID",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = CyberCyan,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ),
                shape = CutCornerShape(topStart = 10.dp, bottomEnd = 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyberCyan,
                    unfocusedBorderColor = CyberPink.copy(alpha = 0.6f),
                    focusedLabelColor = CyberCyan,
                    unfocusedLabelColor = CyberTextDim,
                    cursorColor = CyberYellow,
                    focusedContainerColor = CyberCardBg,
                    unfocusedContainerColor = CyberCardBg
                )
            )

            // Targeta Cyberpunk: PES
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.5.dp,
                        color = CyberPink,
                        shape = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .background(
                        color = CyberCardBg,
                        shape = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "// MASS_PARAM [KG]",
                        color = CyberPink,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "$pes KG",
                        color = CyberYellow,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { if (pes > 20) pes-- },
                            shape = CutCornerShape(6.dp),
                            border = BorderStroke(1.dp, CyberPink),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = CyberPink,
                                containerColor = CyberDarkBg
                            )
                        ) {
                            Text(
                                text = "[-]",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        OutlinedButton(
                            onClick = { if (pes < 300) pes++ },
                            shape = CutCornerShape(6.dp),
                            border = BorderStroke(1.dp, CyberPink),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = CyberPink,
                                containerColor = CyberDarkBg
                            )
                        ) {
                            Text(
                                text = "[+]",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Targeta Cyberpunk: ALÇADA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.5.dp,
                        color = CyberCyan,
                        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .background(
                        color = CyberCardBg,
                        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "// HEIGHT_PARAM [CM]",
                        color = CyberCyan,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "$alcada CM",
                        color = CyberCyan,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        value = alcada.toFloat(),
                        onValueChange = { alcada = it.toInt() },
                        valueRange = 100f..230f,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(
                            thumbColor = CyberPink,
                            activeTrackColor = CyberCyan,
                            inactiveTrackColor = CyberDarkBg
                        )
                    )
                }
            }

            // Botó de Càlcul Cyberpunk
            Button(
                onClick = {
                    val alcadaMetres = alcada.toFloat() / 100f
                    bmi = pes / (alcadaMetres * alcadaMetres)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = CutCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyberYellow,
                    contentColor = CyberDarkBg
                )
            ) {
                Text(
                    text = "> EXECUTE_BMI_SCAN <",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Targeta Cyberpunk: RESULTAT DIAGNÒSTIC
            if (bmi > 0f) {
                val category = getCyberBMICategory(bmi)
                val displayName = name.ifBlank { "SUBJECT_01" }.uppercase(Locale.getDefault())

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = category.color,
                            shape = CutCornerShape(14.dp)
                        )
                        .background(
                            color = CyberCardBg,
                            shape = CutCornerShape(14.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "[ DIAGNOSTIC_OUTPUT // $displayName ]",
                            color = CyberTextDim,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = String.format(Locale.US, "%.1f", bmi),
                            color = category.color,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "BMI_INDEX",
                            color = CyberTextDim,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            color = category.color.copy(alpha = 0.2f),
                            shape = CutCornerShape(6.dp),
                            border = BorderStroke(1.dp, category.color)
                        ) {
                            Text(
                                text = "${category.code} : ${category.label}",
                                color = category.color,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMIScreenPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}
