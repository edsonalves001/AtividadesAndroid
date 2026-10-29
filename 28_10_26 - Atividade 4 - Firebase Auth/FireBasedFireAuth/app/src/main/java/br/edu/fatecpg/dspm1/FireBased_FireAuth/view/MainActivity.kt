package br.edu.fatecpg.dspm1.FireBased_FireAuth.view

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
import br.edu.fatecpg.dspm1.FireBased_FireAuth.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val email = findViewById<EditText>(R.id.edt_email).text
        val senha = findViewById<EditText>(R.id.edt_senha).text
        val salvar = findViewById<Button>(R.id.btn_salvar)
        val esqueciSenhaTxv = findViewById<TextView>(R.id.txv_esqueci_senha)
        auth = FirebaseAuth.getInstance()

        esqueciSenhaTxv.setOnClickListener {
            val intent = Intent(this, EsqueciSenha::class.java)
            startActivity(intent)
        }

        salvar.setOnClickListener{
            auth.signInWithEmailAndPassword(email.toString(),senha.toString()).addOnCompleteListener { task -> if(task.isSuccessful){
                val usuarioLogado = auth.currentUser
                Log.i("Firebase","LOGADO COM SUCESSO!")
                Toast.makeText(this,"Usuário encontrado",Toast.LENGTH_SHORT).show()
               usuarioLogado?.delete()?.addOnCompleteListener { taskExcluir -> if(taskExcluir.isSuccessful){
                Log.i("Firebase","Usuario logado e excluido")
                   Toast.makeText(this,"Usuario Excluido após login",Toast.LENGTH_SHORT).show()
                }else{
                    Log.i("FIREBASE","ERRO AO EXCLUIR USUARIO ${taskExcluir}")
                   Toast.makeText(this,"Erro ao excluir o usuario",Toast.LENGTH_SHORT).show()
               }
               }
                email.clear()
                senha.clear()
            }else{
                Log.i("FIREBASE","ERRO AO LOGAR ${task.exception}")
                Toast.makeText(this,"Usuário não encontrado",Toast.LENGTH_SHORT)
            }
            }
        }

    }
}