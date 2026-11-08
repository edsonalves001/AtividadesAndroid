package br.edu.fatecpg.dsm1.firebaseread.view

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
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth

class RecuperacaodeSenhaActivity : AppCompatActivity(R.layout.activity_recuperacaode_senha) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val auth = FirebaseAuth.getInstance()
        val emailRecuperacao = findViewById<EditText>(R.id.edt_emailrecuperacaodesenha)
        val enviarEmail = findViewById<Button>(R.id.btn_recuperarSenha)
        val voltaraoLogin = findViewById<FloatingActionButton>(R.id.fab_voltarAoLogin)

        voltaraoLogin.setOnClickListener {
            finish()
        }

        enviarEmail.setOnClickListener {
            val recuperacao = emailRecuperacao.text.toString()
            if (recuperacao.isEmpty()) {
                Toast.makeText(this, "Por favor, digite um e-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://fir-d-fireauth.firebaseapp.com")
                .setHandleCodeInApp(true)
                .setAndroidPackageName(packageName, true, "21")
                .build()

            auth.sendPasswordResetEmail(recuperacao,actionCodeSettings).addOnCompleteListener {task->
                if(task.isSuccessful){
                    Log.i("Firebase","Email recuperação")
                    Toast.makeText(this,"Email de recuperação enviado",Toast.LENGTH_SHORT).show()
                    emailRecuperacao.text.clear()
                }else{
                    Log.i("Firebase","Erro email recuperação")
                    Toast.makeText(this,"Não foi possivel ao enviar o email de recuperação",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}