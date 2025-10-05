package com.umb.app_progra_moviles.messages

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umb.app_progra_moviles.R

class MessageFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(R.drawable.bckg),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 120.dp, bottom = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "FORMULARIO",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                ) { pad ->

                    Box(
                        modifier = Modifier
                            .padding(pad)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .padding(horizontal = 16.dp)
                        ) {
                            var nombre by remember { mutableStateOf(TextFieldValue("")) }
                            var apellido by remember { mutableStateOf(TextFieldValue("")) }
                            var edad by remember { mutableStateOf(TextFieldValue("")) }


                            @OptIn(ExperimentalMaterial3Api::class)
                            val tfColors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                                cursorColor = Color.White
                            )

                            OutlinedTextField(
                                value = nombre,
                                onValueChange = { nombre = it },
                                label = { Text("Nombre", color = Color.White) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = tfColors,
                                textStyle = LocalTextStyle.current.copy(color = Color.White)
                            )

                            OutlinedTextField(
                                value = apellido,
                                onValueChange = { apellido = it },
                                label = { Text("Apellido", color = Color.White) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = tfColors,
                                textStyle = LocalTextStyle.current.copy(color = Color.White)
                            )

                            OutlinedTextField(
                                value = edad,
                                onValueChange = { edad = it },
                                label = { Text("Edad", color = Color.White) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = tfColors,
                                textStyle = LocalTextStyle.current.copy(color = Color.White)
                            )

                            Button(
                                onClick = {
                                    val i = Intent(this@MessageFormActivity, MessageDetailActivity::class.java)
                                        .putExtra("nombre", nombre.text.trim())
                                        .putExtra("apellido", apellido.text.trim())
                                        .putExtra("edad", edad.text.trim())
                                    startActivity(i)
                                },
                                enabled = nombre.text.isNotBlank() &&
                                        apellido.text.isNotBlank() &&
                                        edad.text.isNotBlank(),
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text("Registrar", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
