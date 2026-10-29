package br.edu.fatecpg.aulamobile1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityLivrosCadastrados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_livros_cadastrados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val titulo = findViewById<TextView>(R.id.txv_tituloLivro)
        val autor = findViewById<TextView>(R.id.txv_autorLivro)
        val Autor = intent.getStringExtra("autor")
        val Titulo = intent.getStringExtra("titulo")
        val voltar = findViewById<FloatingActionButton>(R.id.fab_voltar)

       titulo.setText(Titulo)
       autor.setText(Autor)
        voltar.setOnClickListener {
            finish()
        }

    }

}