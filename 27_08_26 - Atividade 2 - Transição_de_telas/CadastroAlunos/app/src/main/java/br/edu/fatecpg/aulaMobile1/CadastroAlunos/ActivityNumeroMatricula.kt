package br.edu.fatecpg.aulaMobile1.CadastroAlunos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityNumeroMatricula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_numero_matricula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nomeAluno = intent.getStringExtra("nomeAluno")
        val geradordematricula = findViewById<Button>(R.id.btn_gerador_matricula)
        val resultado = findViewById<TextView>(R.id.txv_resultado)
        var matricula = Math.floor(Math.random()*1000) + 1
        val voltar = findViewById<FloatingActionButton>(R.id.fab_voltar)
        geradordematricula.setOnClickListener {
            resultado.setText("Aluno: $nomeAluno | Matricula: $matricula")
        }
        voltar.setOnClickListener {
            finish()
        }
    }
}