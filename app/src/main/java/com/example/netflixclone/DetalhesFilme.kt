package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixclone.adapter.FilmesAdapter
import com.example.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.example.netflixclone.model.addFilmes
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        toolbarIcone()

        val recycler_outros_filmes = binding.rvOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflixclone-86e06.appspot.com/o/video.jpg?alt=media&token=3776836f-327b-4e58-90cc-21626253379b"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }
    }

    fun toolbarIcone() {
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}