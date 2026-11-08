package br.edu.fatecpg.dsm1.firebaseread.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.dsm1.firebaseread.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class CadastroUsuarioActivity : AppCompatActivity(R.layout.activity_cadastro_usuario) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val auth = FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.edt_cadastroEmail)
        val senha = findViewById<EditText>(R.id.edt_cadastroSenha)
        val cadastrar = findViewById<Button>(R.id.btn_cadastrar)
        val voltaraoLogin = findViewById<FloatingActionButton>(R.id.fab_doCadastroaoLogin)

        voltaraoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        cadastrar.setOnClickListener {
            val emailString = email.text.toString()
            val senhaString = senha.text.toString()

            auth.createUserWithEmailAndPassword(emailString,senhaString).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Log.i("Firebase","${emailString} Cadastrado com sucesso")
                    Toast.makeText(this,"Email Cadastrado",Toast.LENGTH_SHORT).show()
                    email.text.clear()
                    senha.text.clear()
                    startActivity(Intent(this, InserirLivrosActivity::class.java))
                }else{
                    Log.i("Firebase","${emailString} teve falha na hora de ser cadastrado")
                    Toast.makeText(this,"Não foi possivel cadastrar",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}