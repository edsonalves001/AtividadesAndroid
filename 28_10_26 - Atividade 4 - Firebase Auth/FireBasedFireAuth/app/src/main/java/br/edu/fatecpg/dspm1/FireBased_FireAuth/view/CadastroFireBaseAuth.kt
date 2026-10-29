package br.edu.fatecpg.dspm1.FireBased_FireAuth.view

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
import br.edu.fatecpg.dspm1.FireBased_FireAuth.R
import com.google.firebase.auth.FirebaseAuth

class CadastroFireBaseAuth : AppCompatActivity(R.layout.activity_cadastro_fire_base_auth) {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.edt_email_cadastro)
        val senha = findViewById<EditText>(R.id.edt_senha_cadastro)
        val botaoCadastro = findViewById<Button>(R.id.btn_cadastrar)

        botaoCadastro.setOnClickListener {
            var emailString = email.text.toString()
            var senhaString = senha.text.toString()
            auth.createUserWithEmailAndPassword(emailString,senhaString).addOnCompleteListener {task -> if(task.isSuccessful) {
                Log.i("Firebase","${emailString} Cadastrado com sucesso")
                Toast.makeText(this,"Email Cadastrado",Toast.LENGTH_SHORT).show()
                email.text.clear()
                senha.text.clear()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Log.i("FIREBASE","ERRO NO CADASTRADO ${task.exception}")
                Toast.makeText(this,"Email não Cadastrado",Toast.LENGTH_SHORT).show()
            }
           }
        }
    }
}