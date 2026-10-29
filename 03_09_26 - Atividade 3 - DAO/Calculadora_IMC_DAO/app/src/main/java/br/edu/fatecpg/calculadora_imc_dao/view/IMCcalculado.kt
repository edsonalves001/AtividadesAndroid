package br.edu.fatecpg.calculadora_imc_dao.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.calculadora_imc_dao.R
import br.edu.fatecpg.calculadora_imc_dao.dao.PessoaDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IMCcalculado : AppCompatActivity(R.layout.activity_imccalculado) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val people = PessoaDao.procurarinfos()
        val imc = findViewById<TextView>(R.id.txv_imc)
        val situacao = findViewById<TextView>(R.id.txv_infosPeso)
        val fabVolta = findViewById<FloatingActionButton>(R.id.fab_voltar)
        var imcArredondado = "%.2f".format(people.imc)
        imc.setText("O valor do seu IMC é de: $imcArredondado")
        situacao.setText("Sua situação é de: ${people.Valorimc}")

        fabVolta.setOnClickListener {
            finish()
        }
    }
}