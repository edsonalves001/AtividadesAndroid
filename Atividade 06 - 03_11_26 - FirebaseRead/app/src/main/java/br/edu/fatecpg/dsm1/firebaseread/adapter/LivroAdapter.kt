package br.edu.fatecpg.dsm1.firebaseread.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.dsm1.firebaseread.R
import br.edu.fatecpg.dsm1.firebaseread.model.LivroModel
import com.bumptech.glide.Glide

class LivroAdapter (private val livros: List<LivroModel>):
    RecyclerView.Adapter<LivroAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
              val txv_titulo = itemView.findViewById<TextView>(R.id.txv_itemTituloLivro)
              val txv_autor = itemView.findViewById<TextView>(R.id.txv_itemTituloAutor)
              val imgv_url = itemView.findViewById<ImageView>(R.id.imgv_livro)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: ViewHolder,position:Int){
        holder.txv_titulo.text = livros[position].Titulo
        holder.txv_autor.text = livros[position].Autor
        Glide.with(holder.itemView.context).load(livros[position].URL_da_capa).into(holder.imgv_url)
    }
}