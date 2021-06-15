package com.example.netflixclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netflixclone.databinding.ListaFilmesBinding
import com.example.netflixclone.model.Filmes

class FilmesAdapter (val filmes: MutableList<Filmes>): RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val binding = ListaFilmesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        with(holder){
            with(filmes[position]) {
                binding.capaFilmes.setImageResource(capaFilme)
            }
        }
    }

    override fun getItemCount() = filmes.size


    inner class FilmesViewHolder(val binding: ListaFilmesBinding): RecyclerView.ViewHolder(binding.root) {

    }
}