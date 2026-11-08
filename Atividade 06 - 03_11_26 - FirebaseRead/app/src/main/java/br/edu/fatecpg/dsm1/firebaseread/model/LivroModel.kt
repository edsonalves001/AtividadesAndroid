package br.edu.fatecpg.dsm1.firebaseread.model

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class LivroModel(
    val Titulo: String = "",
    val Autor: String = "",
    val URL_da_capa: String = "", ){}



