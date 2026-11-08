package br.edu.fatecpg.dspm1.FireBased_FireAuth

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Firebase.firestore
        val nome = findViewById<EditText>(R.id.edt_nome)
        val cpf = findViewById<EditText>(R.id.edt_cpf)
        val data = findViewById<EditText>(R.id.edt_data)
        val botao = findViewById<Button>(R.id.button)
        var DataSelecionado: Long = 0L

        //Abre um calendario, onde ele pega os dados de dia, mes e ano, e junta, formata e coloca na caixa de texto
        data.setOnClickListener {
            val calendar = Calendar.getInstance()
            val ano = calendar.get(Calendar.YEAR)
            val mes = calendar.get(Calendar.MONTH)
            val dia = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                    val calSelecionado = Calendar.getInstance()
                    calSelecionado.set(Calendar.YEAR, year)
                    calSelecionado.set(Calendar.MONTH, month)
                    calSelecionado.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    calSelecionado.set(Calendar.HOUR_OF_DAY, 0)
                    calSelecionado.set(Calendar.MINUTE, 0)
                    calSelecionado.set(Calendar.SECOND, 0)
                    calSelecionado.set(Calendar.MILLISECOND, 0)
                    DataSelecionado = calSelecionado.timeInMillis

                    val mesFormatado = String.format("%02d", month + 1)
                    val diaFormatado = String.format("%02d", dayOfMonth)
                    data.setText("$diaFormatado/$mesFormatado/$year")


            }, ano, mes, dia).show()
        }


        botao.setOnClickListener {
            val nome2 = nome.text.toString()
            val cpf2 = cpf.text.toString()
            val timestampFirestore = Timestamp(Date(DataSelecionado))
            val hashUsuario = hashMapOf(
                "nome" to nome2,
                "cpf" to cpf2,
                "data-compra" to timestampFirestore
            )
            db.collection("colecao-cliente2").document("doc_cliente1").set(hashUsuario).addOnCompleteListener {task->
                if(task.isSuccessful){
                    Log.d("FIRESTORE","SUCESSO + ${nome} | ${cpf}")
                    Toast.makeText(this,"Sucesso, infos salva ",Toast.LENGTH_SHORT).show()
                }else{
                    Log.d("FIRESTORE","INSUCESSO + ${task.exception}")
                }
            }
        }


    }
}