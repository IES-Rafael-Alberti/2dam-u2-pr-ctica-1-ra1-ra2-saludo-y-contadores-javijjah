package com.hachatml.saludoycontadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                    //todo aquí van las funciones
                Saludar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun IntroductorNombre() {
    /* var tempName by rememberSaveable {
         mutableStateOf("")
     }*/
    //if (showDialog) {
    //todo cambiar a dialog
    //todo los botones pueden ser 1 letra para que quepa todo
    //}
}

@Preview(showBackground = true)
@Composable
fun Saludar() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var contadorAceptar by rememberSaveable { mutableStateOf(0) }
    var contadorLimpiar by rememberSaveable { mutableStateOf(0) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    BotonSaludar(nombre = nombre, showDialog = showDialog,
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonSaludar(nombre:String,showDialog:Boolean) {
    SaludoYContadoresTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showDialog = true })//todo lo mismo que abajo
            {
                Text("Saludar")
            }
            Text(text = "")//todo rellenar si hay nombre
        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = { /*TODO*/ },
                title = {
                    Text(
                        text = "Configuración",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 35.sp,
                        textAlign = TextAlign.Right
                    )
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = nombre,
                            onValueChange = { tempName -> nombre = tempName },//todo esto falla porque al estar elevado el estado hay que elevar la lambda también y llamarla aquí.¿Cómo? no sé
                            label = { Text("Introduce tu nombre") }
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
