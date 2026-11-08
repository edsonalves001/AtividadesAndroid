package br.edu.fatecpg.dsm1.firebaseread.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.dsm1.firebaseread.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class InserirLivrosActivity : AppCompatActivity(R.layout.activity_inserir_livros) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dbBooks = Firebase.firestore
        val titulo = findViewById<EditText>(R.id.edt_TituloLivro)
        val autor = findViewById<EditText>(R.id.edt_autorLivro)
        val capa = findViewById<EditText>(R.id.edt_capaDoLivro)
        val botao = findViewById<Button>(R.id.btn_inserirLivro)
        val lista = findViewById<FloatingActionButton>(R.id.fab_verLista)

        lista.setOnClickListener {
            startActivity(Intent(this, ListadeLivrosActivity::class.java))
        }

        botao.setOnClickListener {
            val tituloString = titulo.text.toString()
            val autorString = autor.text.toString()
            val urlCapaString = capa.text.toString()
            val hashLivro = hashMapOf(
                "Titulo" to tituloString,
                "Autor" to autorString,
                "URL_da_capa" to urlCapaString)
            dbBooks.collection("Livros").document().set(
                hashLivro).addOnCompleteListener { task -> if(task.isSuccessful){
                Log.i("FIRESTORE","Livro cadastrado")
                Toast.makeText(this,"Livro inserido na lista",Toast.LENGTH_SHORT).show()
                titulo.text.clear()
                autor.text.clear()
                capa.text.clear()
                }else{
                    Log.i("FIRESTORE","FALHA NO CADASTRO DO LIVRO")
                    Toast.makeText(this,"Houve alguma falha na hora de inserir o livro", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }
}