package br.edu.fatecpg.calculadora_imc_dao.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.calculadora_imc_dao.R
import br.edu.fatecpg.calculadora_imc_dao.dao.PessoaDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pesoUser = findViewById<EditText>(R.id.edt_peso)
        val alturaUser = findViewById<EditText>(R.id.edt_altura)
        val enviar = findViewById<Button>(R.id.btn_enviar)
        val fabAvancar = findViewById<FloatingActionButton>(R.id.fab_avancar)
        enviar.setOnClickListener {
            val peso = pesoUser.text.toString().toDouble()
            val altura = alturaUser.text.toString().toDouble()
            var imc =  peso / (altura*altura)
            var ValorImc = ""

            if(imc < 18.5){
                ValorImc = "Abaixo do peso"
            }else if (imc >= 18.5 && imc < 25){
                ValorImc = "Peso Normal"
            }else if(imc >= 25 && imc < 30){
                ValorImc = "Sobrepeso"
            }else if(imc >=30 && imc <40 ){
                ValorImc = "Obesidade"
            }else if(imc > 40){
                ValorImc = "Obesidade Grave"
            }

            val retorno = PessoaDao.salvar(peso,altura,ValorImc,imc)
            Log.i("Informações Salvas",retorno)
            Toast.makeText(this,retorno,Toast.LENGTH_SHORT).show()
            pesoUser.text.clear()
            alturaUser.text.clear()
        }
        fabAvancar.setOnClickListener {
            val intent = Intent(this, IMCcalculado::class.java)
            startActivity(intent)
        }
    }
}