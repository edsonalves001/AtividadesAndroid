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
        val edt2 = findViewById<EditText>(R.id.edt_temp)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txvResult = findViewById<TextView>(R.id.txv_resultado)
        val spn = findViewById<Spinner>(R.id.spn_conversoes)

        val temp = edt2.text.toString().toDouble()
        val opcao = spn.selectedItem.toString()

        btnCalcular.setOnClickListener {
            if(opcao.equals("De Celsius (C) para Fahrenheit (F)")){
                val fah = temp * 1.8 + 32
                txvResult.setText("$temp Cº em Fahrenheit é $fah Fº")
                edt2.text.clear()
            }else if(opcao.equals("De Celsius (C) para Kelvin (K)")){
                val kel = temp + 273
                txvResult.setText("$temp Cº em Kelvin é $kel Kº")
                edt2.text.clear()
            }else if(opcao.equals("De Fahrenheit (F) para Celsius (C)")){
                val cel = (temp-32)/1.8
                txvResult.setText("$temp Fº em Celsius é $cel Cº")
                edt2.text.clear()
            }else if(opcao.equals("De Fahrenheit (F) para Kelvin (K)")){
                val kel = (temp - 32) * 5/9 + 273
                txvResult.setText("$temp Fº em Kelvin é $kel Kº")
                edt2.text.clear()
            }else if(opcao.equals("De Kelvin (K) para Celsius (C)")){
                val cel = temp - 273
                txvResult.setText("$temp Kº em Celsius é $cel")
                edt2.text.clear()
            }else if(opcao.equals("De Kelvin (K) para Fahrenheit (F)")){
                val fah = (temp-273)*1.8+32
                txvResult.setText("$temp Kº em Fahrenheit é $fah")
                edt2.text.clear()
            }
        }

    }
}