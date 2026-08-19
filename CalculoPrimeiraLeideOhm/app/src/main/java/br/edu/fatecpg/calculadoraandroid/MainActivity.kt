package br.edu.fatecpg.calculadoraandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val edt1 = findViewById<EditText>(R.id.edt_temp)
        val edt2 = findViewById<EditText>(R.id.edt2_temp)
        val edt3 = findViewById<EditText>(R.id.edt3_temp)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txvResult = findViewById<TextView>(R.id.txv_resultado)

        btnCalcular.setOnClickListener {
            val tensao = edt1.text.toString().toDouble()
            val resistencia = edt2.text.toString().toDouble()
            val corrente = edt3.text.toString().toDouble()

            if(!edt1.text.isNullOrEmpty() && !edt2.text.isNullOrEmpty() && edt3.text.isNullOrEmpty()) {
                val corr = tensao/resistencia
                txvResult.setText("Com a Tensão de $tensao e a resistencia de $resistencia, gera o seguinte valor para a corrente: $corr")
                edt1.text.clear()
                edt2.text.clear()
                edt3.text.clear()
            }else if(!edt1.text.isNullOrEmpty() && edt2.text.isNullOrEmpty() && !edt3.text.isNullOrEmpty()){
                val resi = tensao/corrente
                txvResult.setText("Com a Tensão de $tensao e a corrente de $corrente, gera o seguinte valor para a resistencia: $resi")
                edt1.text.clear()
                edt2.text.clear()
                edt3.text.clear()
            }else if(edt1.text.isNullOrEmpty() && !edt2.text.isNullOrEmpty() && !edt3.text.isNullOrEmpty()){
                val ten = resistencia/corrente
                txvResult.setText("Com a Resistencia de $resistencia e a corrente de $corrente, gera o seguinte valor para a tensão: $ten")
                edt1.text.clear()
                edt2.text.clear()
                edt3.text.clear()
            }else{
                Toast.makeText(applicationContext, "Por favor, adicione pelo menos 2 valores", Toast.LENGTH_SHORT).show()
            }
        }

    }
}