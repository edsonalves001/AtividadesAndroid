package br.edu.fatecpg.dsm1.firebaseread.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.dsm1.firebaseread.R
import br.edu.fatecpg.dsm1.firebaseread.adapter.LivroAdapter
import br.edu.fatecpg.dsm1.firebaseread.model.LivroModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ListadeLivrosActivity : AppCompatActivity(R.layout.activity_listade_livros) {
    private lateinit var adapter: LivroAdapter
    private val listaLivros = mutableListOf<LivroModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sairdaLista = findViewById<FloatingActionButton>(R.id.fab_voltar)
        val repetido = findViewById<RecyclerView>(R.id.rcv_livros)
        adapter = LivroAdapter(listaLivros)
        repetido.layoutManager = LinearLayoutManager(this)
        repetido.adapter = adapter
        buscarLivro()
        sairdaLista.setOnClickListener {
            finish()
        }
    }
    private fun buscarLivro() {
        val db = Firebase.firestore

        db.collection("Livros").get()
            .addOnSuccessListener { resultado ->
                listaLivros.clear()
                for (documento in resultado) {
                    val reserva = documento.toObject(LivroModel::class.java)
                    listaLivros.add(reserva)
                }
                Log.i("FIRESTORE","Dados do banco passados para a list")
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { erro ->
                Log.e("FIRESTORE", "Erro ao buscar reservas", erro)
            }
    }
}