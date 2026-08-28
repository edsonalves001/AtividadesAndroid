package br.edu.fatecpg.calculadoraandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edt1 = findViewById<EditText>(R.id.edt_val1)
        val edt2 = findViewById<EditText>(R.id.edt_val2)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txvResult = findViewById<TextView>(R.id.txv_resultado)
        val spn = findViewById<Spinner>(R.id.spn_operacoes)
        btnCalcular.setOnClickListener{
            val valor1 = edt1.text.toString().toDouble()
            val valor2 = edt2.text.toString().toDouble()
            val op = spn.selectedItem.toString()

            if(op.equals("Soma")){
                val soma =  valor1 + valor2
                txvResult.setText("Resultado da soma: $soma")
                edt1.text.clear()
                edt2.text.clear()
            }else if(op.equals("Subtração")){
                txvResult.clearComposingText()
                val subtracao =  valor1 - valor2
                txvResult.setText("Resultado da subtração: $subtracao")
                edt1.text.clear()
                edt2.text.clear()
            }else if(op.equals("Multiplicação")){
                val multiplicacao =  valor1 * valor2
                txvResult.setText("Resultado da multiplicacão: $multiplicacao")
                edt1.text.clear()
                edt2.text.clear()
            }else if(op.equals("Divisão")){
                val divisao =  valor1 / valor2
                txvResult.setText("Resultado da divisão: $divisao")
                edt1.text.clear()
                edt2.text.clear()
            }else if(op.equals("Todas as operações")){
                val soma =  valor1 + valor2
                val subtracao =  valor1 - valor2
                val multiplicacao =  valor1 * valor2
                val divisao =  valor1 / valor2
                txvResult.setText("Resultado da soma: $soma" + "\nResultado da subtração: $subtracao" + "\nResultado da multiplicacao: $multiplicacao" + "\nResultado da divisao: $divisao" )
                edt1.text.clear()
                edt2.text.clear()
            }

        }
    }
}