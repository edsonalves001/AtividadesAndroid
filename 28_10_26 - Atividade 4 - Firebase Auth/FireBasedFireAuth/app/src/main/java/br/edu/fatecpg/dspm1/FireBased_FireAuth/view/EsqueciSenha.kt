package br.edu.fatecpg.dspm1.FireBased_FireAuth.view

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth

class EsqueciSenha : AppCompatActivity(R.layout.activity_esqueci_senha) {
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
        val emailRecuperacao = findViewById<EditText>(R.id.edt_recuperacao)
        val botaoRecuperacao = findViewById<Button>(R.id.btn_enviarEmail)
        val voltacomoEx = findViewById<FloatingActionButton>(R.id.fab_voltacomteuEx)
        botaoRecuperacao.setOnClickListener {
            val recuperacao = emailRecuperacao.text.toString().trim()

            if (recuperacao.isEmpty()) {
                Toast.makeText(this, "Por favor, digite um e-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://fir-d-fireauth.firebaseapp.com")
                .setHandleCodeInApp(true)
                .setAndroidPackageName(packageName, true, "21")
                .build()

            auth.sendPasswordResetEmail(recuperacao, actionCodeSettings)
                .addOnCompleteListener { taskReset ->
                    if (taskReset.isSuccessful) {
                        Log.i("firebase", "Comando de recuperação disparado com sucesso.")
                        Toast.makeText(this, "Email de recuperação padrão enviado", Toast.LENGTH_LONG).show()
                        emailRecuperacao.text.clear()
                    } else {
                        Log.e("firebase", "Erro no Firebase", taskReset.exception)
                        Toast.makeText(this, "Falha na operação. Verifique a conexão.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        voltacomoEx.setOnClickListener { finish() }
    }
}
