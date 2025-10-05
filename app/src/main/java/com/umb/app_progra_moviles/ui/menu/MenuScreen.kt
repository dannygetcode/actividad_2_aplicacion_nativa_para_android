package com.umb.app_progra_moviles.ui.menu

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.umb.app_progra_moviles.R
import com.umb.app_progra_moviles.messages.MessageFormActivity
import com.umb.app_progra_moviles.web.WebViewActivity

@Composable
fun MenuScreen() {
    val ctx = LocalContext.current
    val menuItems = listOf(
        "Página Institucional (UMB)",
        "Buscar Páginas (por URL)",
        "Mensajes (Formulario y Detalle)"
    )

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
                        .padding(top = 120.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Menú principal",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground

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
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(menuItems) { item ->
                        Card(
                            onClick = {
                                when (item) {
                                    "Página Institucional (UMB)" -> {
                                        val i = Intent(ctx, WebViewActivity::class.java)
                                            .putExtra("url", "https://www.umb.edu.co/")
                                        ctx.startActivity(i)
                                    }
                                    "Buscar Páginas (por URL)" -> {
                                        val i = Intent(ctx, WebViewActivity::class.java)
                                        ctx.startActivity(i)
                                    }
                                    "Mensajes (Formulario y Detalle)" -> {
                                        ctx.startActivity(Intent(ctx, MessageFormActivity::class.java))
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f), // Hace las tarjetas más angostas
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
