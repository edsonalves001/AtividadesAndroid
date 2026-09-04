package br.edu.fatecpg.aulamobile1

import android.content.Intent
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

        val titulo = findViewById<EditText>(R.id.edt_titulo)
        val autor = findViewById<EditText>(R.id.edt_autor)
        val cadastrado = findViewById<Button>(R.id.btn_cadastrar)

        cadastrado.setOnClickListener {
            if(titulo.text.toString().isNullOrEmpty() || autor.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                var actor = autor.text.toString()
                var title = titulo.text.toString()
                val intent = Intent(this, ActivityLivrosCadastrados::class.java)
                intent.putExtra("autor",actor)
                intent.putExtra("titulo",title)
                startActivity(intent)
                titulo.text.clear()
                autor.text.clear()

            }
        }
    }
}