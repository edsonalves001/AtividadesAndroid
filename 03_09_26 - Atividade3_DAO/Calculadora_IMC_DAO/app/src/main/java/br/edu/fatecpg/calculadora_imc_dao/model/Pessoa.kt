package br.edu.fatecpg.calculadora_imc_dao.model

data class Pessoa(
    var peso:Double = 0.0,
    var altura:Double = 0.0,
    var Valorimc:String = "",
    var imc:Double = 0.0
)
