package com.umb.app_progra_moviles.messages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.umb.app_progra_moviles.R

class MessageDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val edad = intent.getStringExtra("edad") ?: ""

        setContent {
            DetailScreen(nombre, apellido, edad)
        }
    }
}

@Composable
private fun DetailScreen(nombre: String, apellido: String, edad: String) {
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
                        text = "DETALLE",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 22.sp
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

                OutlinedCard(
                    shape = RoundedCornerShape(20.dp),
                    border = CardDefaults.outlinedCardBorder().copy(
                        width = 2.dp,
                        brush = androidx.compose.ui.graphics.SolidColor(Color.White)
                    ),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        DataRow(label = "Nombre", value = nombre)
                        DataRow(label = "Apellido", value = apellido)
                        DataRow(label = "Edad", value = edad)
                    }
                }
            }
        }
    }
}

@Composable
private fun DataRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 44.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }


        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 44.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
