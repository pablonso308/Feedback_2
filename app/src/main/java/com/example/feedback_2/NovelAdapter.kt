package com.example.feedback_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NovelAdapter(
    private var novels: List<Novel> = listOf(),
    private val onItemClickListener: (Novel) -> Unit,
    private val onFavoriteClickListener: (Novel) -> Unit,
    private val onDeleteClickListener: (Novel) -> Unit
) : RecyclerView.Adapter<NovelAdapter.NovelViewHolder>() {

    // ViewHolder que mantiene las referencias a las vistas para cada elemento de la lista
    inner class NovelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        val authorTextView: TextView = itemView.findViewById(R.id.text_view_author)
        val yearTextView: TextView = itemView.findViewById(R.id.text_view_year)
        val favoriteImageView: ImageView = itemView.findViewById(R.id.image_view_favorite)
        val deleteButton: Button = itemView.findViewById(R.id.button_delete)

        // Función para enlazar los datos con el ítem de la lista
        fun bind(novel: Novel) {
            titleTextView.text = novel.title
            authorTextView.text = novel.author
            yearTextView.text = novel.year.toString()

            // Marcar como favorito
            if (novel.isFavorite) {
                favoriteImageView.setImageResource(R.drawable.ic_launcher_foreground) // Icono de novela favorita
            } else {
                favoriteImageView.setImageResource(R.drawable.ic_launcher_foreground) // Icono de no favorita
            }

            // Evento de clic para mostrar detalles de la novela
            itemView.setOnClickListener {
                onItemClickListener(novel)
            }

            // Evento de clic para marcar/desmarcar como favorito
            favoriteImageView.setOnClickListener {
                onFavoriteClickListener(novel)
            }

            // Evento de clic para eliminar la novela
            deleteButton.setOnClickListener {
                onDeleteClickListener(novel)
            }
        }
    }

    // Crea una nueva vista para cada ítem de la lista (layout personalizado)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.novel_item, parent, false)
        return NovelViewHolder(itemView)
    }

    // Se llama por cada elemento en la lista para enlazar los datos con el ViewHolder
    override fun onBindViewHolder(holder: NovelViewHolder, position: Int) {
        val currentNovel = novels[position]
        holder.bind(currentNovel) // Enlaza la novela actual con el ViewHolder
    }

    // Devuelve el número de elementos en la lista
    override fun getItemCount(): Int {
        return novels.size
    }

    // Actualiza la lista de novelas y notifica al adaptador que los datos han cambiado
    fun setNovels(novels: List<Novel>) {
        this.novels = novels
        notifyDataSetChanged() // Notifica al RecyclerView que debe actualizarse
    }
}
