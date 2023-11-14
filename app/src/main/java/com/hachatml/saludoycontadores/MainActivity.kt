package com.hachatml.saludoycontadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hachatml.saludoycontadores.ui.theme.SaludoYContadoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaludoYContadoresTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Saludar()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Saludar() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var ButtonText by rememberSaveable { mutableStateOf("Saludar") }
    var contadorAceptar by rememberSaveable { mutableStateOf(0) }
    var contadorCancelar by rememberSaveable { mutableStateOf(0) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showDialogLambda: () -> Unit = {
        showDialog = !showDialog
        ButtonText = "A:$contadorAceptar C:$contadorCancelar"
    }
    var nombreUpdaterLambda: (String) -> Unit = { tempName ->
        nombre = tempName
    }
    var nombreCleaner: () -> Unit = {
        nombre = ""
    }
    var contadorAceptarLambda: () -> Unit = {
        contadorAceptar++
    }
    var contadorCancelarLambda: () -> Unit = {
        contadorCancelar++
    }
    BotonSaludar(
        nombre = nombre,
        ButtonText = ButtonText,
        showDialog = showDialog,
        showDialogLambda = showDialogLambda,
        nombreUpdaterLambda = nombreUpdaterLambda,
        nombreCleaner = nombreCleaner,
        contadorAceptarLambda = contadorAceptarLambda,
        contadorCancelarLambda = contadorCancelarLambda,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonSaludar(
    nombre: String,
    ButtonText: String,
    showDialog: Boolean,
    showDialogLambda: () -> Unit,
    nombreUpdaterLambda: (String) -> Unit,
    nombreCleaner: () -> Unit,
    contadorAceptarLambda: () -> Unit,
    contadorCancelarLambda: () -> Unit
) {
    SaludoYContadoresTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = showDialogLambda)
            {
                Text(ButtonText)
            }
            if (nombre.isNotBlank()) {
                Text(text = " Hola, $nombre!")
            }//todo rellenar si hay nombre
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = showDialogLambda,
                confirmButton = {
                    Button(onClick = {
                        showDialogLambda()
                        contadorAceptarLambda()
                    }) {
                        Text("A")
                    }
                },
                dismissButton = {
                    Button(onClick = nombreCleaner) {
                        Text("L")
                    }
                    Button(onClick = {
                        showDialogLambda()
                        contadorCancelarLambda()
                    }) {
                        Text("C") //todo preguntar qué debe hacer cancelar
                    }
                },
                title = {
                    Text(
                        text = "Configuración",
                        fontSize = 35.sp,
                        textAlign = TextAlign.Right
                    )
                },
                text = {
                    Column {
                        TextField(
                            value = nombre,
                            onValueChange = nombreUpdaterLambda,
                            label = { Text("Introduce tu nombre") }
                        )
                    }
                },
            )
        }
    }
}
