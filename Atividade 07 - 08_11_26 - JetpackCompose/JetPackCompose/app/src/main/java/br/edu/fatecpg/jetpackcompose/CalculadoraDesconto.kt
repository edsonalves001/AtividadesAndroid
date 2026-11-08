package br.edu.fatecpg.jetpackcompose

fun calcularPrecoComDesconto(preco: Double, percentual: Double = 10.0
): Double {
    val desconto = preco * percentual / 100.0
    return preco - desconto
}
