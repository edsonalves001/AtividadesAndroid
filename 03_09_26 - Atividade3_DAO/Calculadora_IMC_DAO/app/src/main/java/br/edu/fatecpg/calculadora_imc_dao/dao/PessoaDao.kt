package br.edu.fatecpg.calculadora_imc_dao.dao

import br.edu.fatecpg.calculadora_imc_dao.model.Pessoa

class PessoaDao {
    companion object{
        private var PessoaCalc = Pessoa()

        fun salvar(peso:Double,altura:Double,Valorimc:String,imc:Double):String{
            PessoaCalc = Pessoa(peso,altura,Valorimc,imc)
            return "Infos salvas"
        }

        fun procurarinfos() : Pessoa {
            return PessoaCalc
        }

    }
}