package com.example.netflixclone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixclone.adapter.FilmesAdapter
import com.example.netflixclone.databinding.ActivityListaFilmesBinding
import com.example.netflixclone.model.addFilmes
import com.example.netflixclone.onclick.OnItemClickListener
import com.example.netflixclone.onclick.addOnItemClickListener
import com.google.firebase.auth.FirebaseAuth

class ListaFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_filmes = binding.rvListaFilmes
        recycler_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        recycler_filmes.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                when {
                    position == 0 -> detalhesFilme()
                }
            }
        })
    }

//    private fun statusbarColar() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.statusBarColor(resources.getColor(R.color.black, this.theme))
//        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor(resources.getColor(R.color.black))
//        }
//    }

    private fun detalhesFilme() {
        val intent = Intent(this, DetalhesFilme::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                redirecionaParaLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun redirecionaParaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}


