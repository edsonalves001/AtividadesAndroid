package br.edu.fatecpg.aulaMobile1.CadastroAlunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val nome = findViewById<EditText>(R.id.edt_nomeAluno)
        val matricular = findViewById<Button>(R.id.btn_matricular)

        matricular.setOnClickListener {
            if(nome.text.toString().isNullOrEmpty()){
                Toast.makeText(this,"Digite seu nome para prosseguir com a matricula", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, ActivityNumeroMatricula::class.java)
                intent.putExtra("nomeAluno",nome.text.toString())
                startActivity(intent)
                nome.text.clear()
            }
        }
    }
}