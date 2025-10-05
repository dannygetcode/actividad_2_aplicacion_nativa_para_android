package com.umb.app_progra_moviles.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class WebViewActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialUrl = intent.getStringExtra("url") ?: "https://www.umb.edu.co/"
        setContent {
            var url by remember { mutableStateOf(TextFieldValue(initialUrl)) }
            val hasPreset = intent.getStringExtra("url") != null
            var webViewRef by remember { mutableStateOf<WebView?>(null) }

            Scaffold(
                topBar = {
                    Surface(tonalElevation = 2.dp) {
                        Text(
                            text = "Navegador",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }
                }
            ) { pad ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(pad),
                    verticalArrangement = Arrangement.Top
                ) {
                    if (!hasPreset) {
                        Row(Modifier.padding(8.dp)) {
                            OutlinedTextField(
                                value = url,
                                onValueChange = { url = it },
                                modifier = Modifier.weight(1f),
                                label = { Text("https://…") }
                            )
                            Spacer(Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    val target = normalize(url.text)
                                    if (target.isNotBlank()) {
                                        webViewRef?.loadUrl(target)
                                    }
                                },
                                enabled = url.text.isNotBlank()
                            ) { Text("Ir") }
                        }
                    }

                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            WebView(ctx).apply {
                                webViewRef = this
                                settings.javaScriptEnabled = true
                                webViewClient = WebViewClient()
                                loadUrl(normalize(url.text.ifBlank { initialUrl }))
                            }
                        },
                        update = { wv ->
                            // Mantén sincronía si cambia el texto
                            val target = normalize(url.text)
                            if (target.isNotBlank() && wv.url != target) {
                                wv.loadUrl(target)
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun normalize(raw: String): String {
    val t = raw.trim()
    if (t.isEmpty()) return t

    return if (t.startsWith("http://") || t.startsWith("https://")) t else "https://$t"
}
