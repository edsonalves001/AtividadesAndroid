package br.edu.fatecpg.jetpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun CalculadoraDescontoScreen() {
    var percentualPrdo by remember {
        mutableStateOf("")
    }
    var precoTexto by remember {
        mutableStateOf("")
    }
    var resultado by remember {
        mutableStateOf("Informe um preço e toque em calcular.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(222.dp))

        Text(
            text = "Calculadora de Desconto",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = precoTexto,
            onValueChange = { novoTexto ->
                precoTexto = novoTexto
            },
            label = { Text("Preço do produto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(22.dp))
        TextField(
            value = percentualPrdo,
            onValueChange = { textoPercentual ->
                percentualPrdo = textoPercentual
            },
            label = { Text("Percentual de desconto do produto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val preco = precoTexto.trim().replace(",", ".").toDoubleOrNull()
                val desconto = percentualPrdo.trim().replace(",",".").toDoubleOrNull()

                if (preco == null || preco <= 0 || desconto == null || desconto <= 0) {
                    resultado = "Digite um preço válido."
                } else {
                    val precoFinal =
                        calcularPrecoComDesconto(preco,desconto)
                    resultado = String.format(
                        Locale("pt", "BR"),
                        "Preço final: R$ %.2f",
                        precoFinal
                    )
                }
            }
        ) {
            Text("CALCULAR")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = resultado,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
