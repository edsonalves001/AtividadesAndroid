package br.edu.fatecpg.dsm1.firebaseread.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.dsm1.firebaseread.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val auth = FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.edt_email)
        val senha = findViewById<EditText>(R.id.edt_senha)
        val esqueceuSenha = findViewById<TextView>(R.id.txv_esqueceusenha)
        val cadastrar = findViewById<TextView>(R.id.txv_secadastrar)
        val logar = findViewById<Button>(R.id.btn_logar)

        esqueceuSenha.setOnClickListener {
            startActivity(Intent(this, RecuperacaodeSenhaActivity::class.java))
        }

        cadastrar.setOnClickListener {
            startActivity(Intent(this,CadastroUsuarioActivity::class.java))
        }

        logar.setOnClickListener {
            val emailString = email.text.toString()
            val senhaString = senha.text.toString()

            auth.signInWithEmailAndPassword(emailString,senhaString).addOnCompleteListener {
                task -> if(task.isSuccessful){
                Log.i("Firebase","LOGADO COM SUCESSO!")
                Toast.makeText(this,"Bem vindo!!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, InserirLivrosActivity::class.java)
                startActivity(intent)
                finish()
                }else{
                 Log.i("Firebase","FALHA NO LOGIN")
                 Toast.makeText(this,"Usuário não cadastrado",Toast.LENGTH_SHORT).show()
                 }
            }



        }

    }
}